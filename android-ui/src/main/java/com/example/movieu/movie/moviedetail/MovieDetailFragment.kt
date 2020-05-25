package com.example.movieu.movie.moviedetail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.movieu.R
import com.example.movieu.dependencyInjection.ViewModelFactory
import com.example.movieu.movie.MovieDetailViewModel
import javax.inject.Inject

class MovieDetailFragment : Fragment() {

    private lateinit var viewModel: MovieDetailViewModel

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        viewModel = ViewModelProvider(this, viewModelFactory).get(MovieDetailViewModel::class.java)
        return inflater.inflate(R.layout.fragment_movie_detail, container, false)

    }
}

