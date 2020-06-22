package com.example.movieu.movie.movielist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.tmdbmovie.model.Result
import com.example.domain.tmdbmovie.usecases.movielist.RefreshTMDbMoviesUseCase
import com.example.domain.tmdbmovie.usecases.movielist.ObserveTMDbMoviesUseCase
import io.reactivex.subscribers.DisposableSubscriber
import kotlinx.coroutines.launch
import javax.inject.Inject

class MovieListViewModel @Inject constructor(
    private val refreshTMDbMoviesUseCase: RefreshTMDbMoviesUseCase,
    private val observeTMDbMoviesUseCase: ObserveTMDbMoviesUseCase
) : ViewModel() {

    var currentMovies: String? = null

    private val movieState = MutableLiveData(MovieListState())

    fun getState(): LiveData<MovieListState> = movieState

    init {
        observeTMDbMovies()
    }

    fun onNewMoviesSearched(newMovies: String) {
        this.currentMovies = newMovies
        observeTMDbMoviesUseCase.onSearchTermChanged(newMovies)
        refreshTMDbMoviesAndUpdate()
    }

    private fun observeTMDbMovies() {
        observeTMDbMoviesUseCase.invokeUseCase(object : DisposableSubscriber<List<Result>>() {
            override fun onComplete() {

            }

            override fun onNext(t: List<Result>?) {
                movieState.value = movieState.value!!.copy(feed = t)
            }

            override fun onError(t: Throwable?) {
                throw Exception("Subscription failed at ${t?.localizedMessage}")
            }
        }, params = null)
    }

    private fun refreshTMDbMoviesAndUpdate() {
        viewModelScope.launch {
            currentMovies?.let {
                refreshTMDbMoviesUseCase.invokeUseCase(
                    params = RefreshTMDbMoviesUseCase.Params(
                        it
                    )
                )
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        observeTMDbMoviesUseCase.dispose()
    }
}