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
    private lateinit var ratingsAdapter: MovieRatingsAdapter
    private lateinit var binding: FragmentMovieDetailBinding

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private var imdbID: String = ""

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
        binding.recListRatings.adapter = null
    }

    override fun onStart() {
        super.onStart()

        observeViewModel()
        //setUpMovieRatingsAdapter()
    }
/*
    private fun setUpMovieRatingsAdapter() {
        ratingsAdapter = MovieRatingsAdapter()
        binding.recListRatings.adapter = ratingsAdapter

        viewModel.getState().observe(
            viewLifecycleOwner,
            Observer { movieDetailState ->
                if (movieDetailState != null) {
                    ratingsAdapter.submitList(movieDetailState.OMDbBaseInformation?.ratings)
                }
            }
        )
    }
*/
    @SuppressLint("SetTextI18n")
    private fun observeViewModel() {
        viewModel.getState().observe(
            viewLifecycleOwner,
            Observer { t ->
                if (t != null) {
                    val posterPath = "http://image.tmdb.org/t/p/w500/${t.tmDbMovieDetail?.posterPath}"
                    binding.movieDetailView.load(posterPath)
                    binding.lblMovieTitle.text = t.tmDbMovieDetail?.title
                    binding.lblMovieYear.text = t.tmDbMovieDetail?.releaseDate
                    binding.lblMovieRuntime.text = t.tmDbMovieDetail?.runtime.toString()
                    binding.lblMoviePlot.text = t.tmDbMovieDetail?.overview
                    binding.lblMovieGenre.text = "Genre: " + t.tmDbMovieDetail?.genres
                    binding.lblMovieLanguage.text = "Language: " + t.tmDbMovieDetail?.originalLanguage
                    binding.lblMovieCast.text = "Budget: " + t.tmDbMovieDetail?.budget
                    binding.lblMovieDirector.text = "Director: " + t.tmDbMovieDetail?.adult
                }
            }
        )
    }
}