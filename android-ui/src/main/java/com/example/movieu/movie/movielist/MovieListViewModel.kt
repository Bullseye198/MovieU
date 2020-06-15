package com.example.movieu.movie.movielist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cm.base.executor.AppCoroutineDispatchers
import com.example.domain.movie.model.Movie
import com.example.domain.movie.usecases.ObserveMoviesUseCase
import com.example.domain.movie.usecases.RefreshMoviesUseCase
import com.example.domain.tmdbmovie.model.Result
import com.example.domain.tmdbmovie.usecases.RefreshTMDbMoviesUseCase
import com.example.domain.tmdbmovie.usecases.RequestTMDbMoviesUseCase
import io.reactivex.subscribers.DisposableSubscriber
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class MovieListViewModel @Inject constructor(
    private val observeMoviesUseCase: ObserveMoviesUseCase,
    private val refreshMoviesUseCase: RefreshMoviesUseCase,
    private val appCoroutineDispatchers: AppCoroutineDispatchers,
    private val refreshTMDbMoviesUseCase: RefreshTMDbMoviesUseCase,
    private val requestTMDbMoviesUseCase: RequestTMDbMoviesUseCase
) : ViewModel() {

    //private val movieState = MutableLiveData(MovieListState())

    //fun getState(): LiveData<MovieListState> = movieState

    //var currentMovies: String? = null

    private val movieListState = MutableLiveData<List<Result>>()
    val movieList: LiveData<List<Result>> get() = movieListState

    var title: String = ""

    init {
        getMovies()
    }

    fun onTMDbMovieSearched(titleToSearchFor: String) {
        this.title = titleToSearchFor
        getMovies()
    }

    private fun getMovies() {
        viewModelScope.launch {
            val movies = withContext(appCoroutineDispatchers.io) {
                requestTMDbMoviesUseCase.requestTMDbMovies(title)
            }
            movieListState.value = movies
        }
        refreshMoviesAndUpdate()
    }

    private fun refreshMoviesAndUpdate() {
    viewModelScope.launch {
        val movies = withContext(appCoroutineDispatchers.io) {
            refreshTMDbMoviesUseCase.refresh(title)
            requestTMDbMoviesUseCase.requestTMDbMovies(title)
        }
        movieListState.value = movies
    }
    }

/*
    fun onNewMoviesSearched(newMovies: String) {
        this.currentMovies = newMovies
        observeMoviesUseCase.onSearchTermChanged(newMovies)
        refreshMoviesAndUpdate()
    }

    private fun getMovies() {
        observeMoviesUseCase.invokeUseCase(object : DisposableSubscriber<List<Movie>>() {
            override fun onComplete() {

            }

            override fun onNext(t: List<Movie>?) {
                movieState.value = movieState.value!!.copy(feed = t)
            }

            override fun onError(t: Throwable?) {
                throw Exception("Subscription failed at ${t?.localizedMessage}")
            }
        }, params = null)
    }

    private fun refreshMoviesAndUpdate() {
        viewModelScope.launch() {
            currentMovies?.let { refreshMoviesUseCase.invokeUseCase(params = RefreshMoviesUseCase.Params(it)) }
        }
    }

    override fun onCleared() {
        super.onCleared()
        observeMoviesUseCase.dispose()
    }

 */
}