package com.example.movieu.movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cm.base.executor.AppCoroutineDispatchers
import com.example.domain.movie.model.Movie
import com.example.domain.usecases.RefreshMoviesUseCase
import com.example.domain.usecases.ObserveMoviesUseCase
import io.reactivex.subscribers.DisposableSubscriber
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

class MovieListViewModel @Inject constructor(
    private val observeMoviesUseCase: ObserveMoviesUseCase,
    private val refreshMoviesUseCase: RefreshMoviesUseCase,
    private val appCoroutineDispatchers: AppCoroutineDispatchers
) : ViewModel() {

    private val movieListState = MutableLiveData<List<Movie>>()
    val movieList: LiveData<List<Movie>> get() = movieListState

    var currentMovies: String? = null
    var title: String = ""


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
                movieListState.value = t
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