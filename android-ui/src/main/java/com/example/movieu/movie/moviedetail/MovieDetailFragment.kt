package com.example.movieu.movie.moviedetail

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import coil.api.load
import com.example.movieu.databinding.FragmentMovieDetailBinding
import com.example.movieu.dependencyInjection.ViewModelFactory
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class MovieDetailFragment : DaggerFragment() {

    private lateinit var viewModel: MovieDetailViewModel
    private lateinit var genreAdapter: MovieGenreAdapter
    private lateinit var binding: FragmentMovieDetailBinding

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private var imdbID: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        imdbID = MovieDetailFragmentArgs.fromBundle(requireArguments()).movieID
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMovieDetailBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(this, viewModelFactory).get(MovieDetailViewModel::class.java)
        viewModel.handleEvent(MovieDetailEvent.OnStart(imdbID))
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding.recListGenre.adapter = null
    }

    override fun onStart() {
        super.onStart()

        observeViewModel()
        setUPMovieGenreAdapter()
    }


        private fun setUPMovieGenreAdapter() {
            genreAdapter = MovieGenreAdapter()
            binding.recListGenre.adapter = genreAdapter

            viewModel.getState().observe(
                viewLifecycleOwner,
                Observer { movieDetailState ->
                    if (movieDetailState != null) {
                        genreAdapter.submitList(movieDetailState.tmDbMovieDetail?.genres)
                    }
                }
            )
        }

    @SuppressLint("SetTextI18n")
    private fun observeViewModel() {
        viewModel.getState().observe(
            viewLifecycleOwner,
            Observer { t ->
                if (t != null) {
                    val posterPath =
                        "http://image.tmdb.org/t/p/w500/${t.tmDbMovieDetail?.posterPath}"
                    binding.movieDetailView.load(posterPath)
                    val backdropPath =
                        "https://image.tmdb.org/t/p/w780/${t.tmDbMovieDetail?.backdropPath}"
                    binding.movieDetailBackdrop.load(backdropPath)
                    binding.lblMovieTitle.text = t.tmDbMovieDetail?.title
                    binding.lblMovieYear.text = t.tmDbMovieDetail?.releaseDate
                    binding.lblMovieRuntime.text = t.tmDbMovieDetail?.runtime.toString() + " Min"
                    binding.lblMoviePlot.text = t.tmDbMovieDetail?.overview
                    binding.lblMovieGenre.text = "Genre: "
                    binding.lblMovieLanguage.text =
                        "Language: " + t.tmDbMovieDetail?.originalLanguage
                    binding.lblMovieCast.text = "Budget: " + t.tmDbMovieDetail?.budget + " Dollars"
                    binding.lblMovieDirector.text = "IMDb Rating: " + t.tmDbMovieDetail?.imdbRating
                    binding.lblMoviePopularity.text = "Popularity: " + t.tmDbMovieDetail?.popularity
                }
            }
        )
    }
}