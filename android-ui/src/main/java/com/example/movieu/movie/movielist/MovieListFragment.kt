package com.example.movieu.movie.movielist

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.movieu.R
import com.example.movieu.dependencyInjection.ViewModelFactory
import com.example.movieu.movie.MovieListViewModel
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class MovieListFragment : DaggerFragment() {

    private lateinit var viewModel: MovieListViewModel


    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProvider(this,viewModelFactory).get(MovieListViewModel::class.java)
        return inflater.inflate(R.layout.fragment_movie_list, container, false)
    }
}
