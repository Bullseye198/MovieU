package com.example.movieu.movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.movie.model.Movie
import com.example.domain.usecases.ObserveMoviesUseCase
import com.example.domain.usecases.RefreshMoviesUseCase
import io.reactivex.subscribers.DisposableSubscriber
import kotlinx.coroutines.launch
import javax.inject.Inject

class MovieListViewModel @Inject constructor(
    private val observeMoviesUseCase: ObserveMoviesUseCase,
    private val refreshMoviesUseCase: RefreshMoviesUseCase
) : ViewModel() {

    private val movieState = MutableLiveData(MovieState())

    fun getState(): LiveData<MovieState> = movieState

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
            currentMovies?.let { refreshMoviesUseCase.refresh(it) }
        }
    }

    override fun onCleared() {
        super.onCleared()
        observeMoviesUseCase.dispose()
    }
}