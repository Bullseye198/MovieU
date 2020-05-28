package com.example.movieu.movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cm.base.executor.AppCoroutineDispatchers
import com.example.domain.movie.model.Movie
import com.example.domain.usecases.RefreshMoviesUseCase
import com.example.domain.usecases.RequestMoviesUseCase
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.Exception
import javax.inject.Inject

class MovieListViewModel @Inject constructor(
    private val requestMoviesUseCase: RequestMoviesUseCase,
    private val refreshMoviesUseCase: RefreshMoviesUseCase,
    private val appCoroutineDispatchers: AppCoroutineDispatchers
) : ViewModel() {

    private val movieListState = MutableLiveData<List<Movie>>()
    val movieList: LiveData<List<Movie>> get() = movieListState

    var title: String = ""


    init {
        getMovies()
    }

    fun onMovieSearched(titleToSearchFor: String) {
        this.title = titleToSearchFor
        getMovies()
    }

    private fun getMovies() {
        viewModelScope.launch {
            val movies = withContext(appCoroutineDispatchers.io) {
                requestMoviesUseCase.requestMovies(title)
            }
            movieListState.value = movies
        }
        refreshMoviesAndUpdate()
    }

    private fun refreshMoviesAndUpdate() {
        viewModelScope.launch() {
            val movies = withContext(appCoroutineDispatchers.io) {
                refreshMoviesUseCase.refresh(title)
                requestMoviesUseCase.requestMovies(title)
            }
            movieListState.value = movies
        }
    }
}