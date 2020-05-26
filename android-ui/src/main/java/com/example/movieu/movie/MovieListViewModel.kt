package com.example.movieu.movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cm.base.executor.AppCoroutineDispatchers
import com.example.domain.movie.model.Movie
import com.example.domain.usecases.OnGetMoviesUseCase
import com.example.movieu.movie.movielist.MovieListEvent
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class MovieListViewModel @Inject constructor(
    private val onGetMoviesUseCase: OnGetMoviesUseCase,
    private val appCoroutineDispatchers: AppCoroutineDispatchers
) : ViewModel() {

    private val movieListState = MutableLiveData<List<Movie>>()
    val movieList: LiveData<List<Movie>> get() = movieListState


    init {
        getMovies()
    }

    private fun getMovies() {
        viewModelScope.launch {
            val movies = withContext(appCoroutineDispatchers.io) {
                onGetMoviesUseCase.getMovies()
            }
            movieListState.value = movies
        }
    }
}