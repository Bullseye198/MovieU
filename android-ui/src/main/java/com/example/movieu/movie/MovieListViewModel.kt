package com.example.movieu.movie

import androidx.lifecycle.ViewModel
import com.example.domain.usecases.OnGetMoviesUseCase
import com.example.movieu.movie.movielist.MovieListEvent
import javax.inject.Inject

class MovieListViewModel @Inject constructor(
    private val onGetMoviesUseCase: OnGetMoviesUseCase
) : ViewModel() {


}