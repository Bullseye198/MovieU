package com.example.movieu.movie.moviedetail

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import coil.api.load
import com.example.movieu.R
import com.example.movieu.databinding.FragmentMovieDetailBinding
import com.example.movieu.dependencyInjection.ViewModelFactory
import com.example.movieu.movie.MovieDetailViewModel
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_movie_detail.*
import javax.inject.Inject

class MovieDetailFragment : DaggerFragment() {

    private lateinit var viewModel: MovieDetailViewModel
    private lateinit var listViewAdapter: ArrayAdapter<String>

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

    override fun onStart() {
        super.onStart()

        observeViewModel()
    }

    @SuppressLint("SetTextI18n")
    private fun observeViewModel() {
        viewModel.movie.observe(
            viewLifecycleOwner,
            Observer { movie ->
                movieDetailView.load(movie.poster.replace("http:", "https:"))
                binding.lblMovieTitle.text = movie.title
                binding.lblMovieYear.text = movie.year
                binding.lblMovieRuntime.text = movie.runtime
                binding.lblMoviePlot.text = movie.plot
                binding.lblMovieGenre.text = "Genre: " + movie.genre
                binding.lblMovieLanguage.text = "Language: " + movie.language
                binding.lblMovieCast.text = "Cast: " + movie.actors
                binding.lblMovieDirector.text = "Director: " + movie.director
                binding.lblMovieRatings.text = "Ratings: " + movie.ratings
            }
        )
    }
}