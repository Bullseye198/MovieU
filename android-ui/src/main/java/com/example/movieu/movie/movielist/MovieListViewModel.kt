package com.example.movieu.movie.movielist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.movie.model.Movie
import com.example.domain.usecases.ObserveMoviesUseCase
import com.example.domain.usecases.RefreshMoviesUseCase
import com.example.movieu.movie.moviedetail.MovieDetailState
import io.reactivex.subscribers.DisposableSubscriber
import kotlinx.coroutines.launch
import javax.inject.Inject

class MovieListViewModel @Inject constructor(
    private val observeMoviesUseCase: ObserveMoviesUseCase,
    private val refreshMoviesUseCase: RefreshMoviesUseCase
) : ViewModel() {

    private val movieState = MutableLiveData(MovieListState())

    fun getState(): LiveData<MovieListState> = movieState

    var currentMovies: String? = null

    init {
        getMovies()
    }

    fun onNewMoviesSearched(newMovies: String) {
        this.currentMovies = newMovies
        observeMoviesUseCase.onSearchTermChanged(newMovies)
        refreshMoviesAndUpdate()
    }

    private fun getMovies() {
        observeMoviesUseCase.requestMovies(object : DisposableSubscriber<List<Movie>>() {
            override fun onComplete() {

            }

            override fun onNext(t: List<Movie>?) {
                movieState.value = movieState.value!!.copy(feed = t)
            }

            override fun onError(t: Throwable?) {
                throw Exception("Subscription failed at ${t?.localizedMessage}")
            }
        })
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
}