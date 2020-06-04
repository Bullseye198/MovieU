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
import com.example.movieu.movie.MovieDetailViewModel
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_movie_detail.*
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
        rec_list_ratings.adapter = null
    }

    override fun onStart() {
        super.onStart()

        observeViewModel()
        setUpMovieRatingsAdapter()
    }

    private fun setUpMovieRatingsAdapter() {
        ratingsAdapter = MovieRatingsAdapter()
        rec_list_ratings.adapter = ratingsAdapter

        viewModel.getState().observe(
            viewLifecycleOwner,
            Observer { t ->
                if (t != null) {
                    ratingsAdapter.submitList(t.movieDetail?.ratings)
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
                    movieDetailView.load(t.movieDetail?.poster?.replace("http:", "https:"))
                    binding.lblMovieTitle.text = t.movieDetail?.title
                    binding.lblMovieYear.text = t.movieDetail?.year
                    binding.lblMovieRuntime.text = t.movieDetail?.runtime
                    binding.lblMoviePlot.text = t.movieDetail?.plot
                    binding.lblMovieGenre.text = "Genre: " + t.movieDetail?.genre
                    binding.lblMovieLanguage.text = "Language: " + t.movieDetail?.language
                    binding.lblMovieCast.text = "Cast: " + t.movieDetail?.actors
                    binding.lblMovieDirector.text = "Director: " + t.movieDetail?.director
                }
            }
        )
    }
}