package com.example.movieu.movie.moviedetail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import coil.api.load
import com.example.movieu.R
import com.example.movieu.dependencyInjection.ViewModelFactory
import com.example.movieu.movie.MovieDetailViewModel
import dagger.android.DaggerApplication
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_movie_detail.*
import javax.inject.Inject

class MovieDetailFragment : DaggerFragment() {

    private lateinit var viewModel: MovieDetailViewModel

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

        viewModel = ViewModelProvider(this, viewModelFactory).get(MovieDetailViewModel::class.java)
        viewModel.handleEvent(MovieDetailEvent.OnStart(imdbID))
        return inflater.inflate(R.layout.fragment_movie_detail, container, false)
    }

    override fun onStart() {
        super.onStart()

        observeViewModel()
    }

    private fun observeViewModel() {
        viewModel.movie.observe(
            viewLifecycleOwner,
            Observer { movie ->
                movieDetailView.load(movie.poster.replace("http:", "https:"))
            }
        )
    }
}

