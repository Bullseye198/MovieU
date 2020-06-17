package com.example.movieu.movie.moviedetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cm.base.executor.AppCoroutineDispatchers
import com.example.domain.movie.usecases.RefreshMovieDetailUseCase
import com.example.domain.tmdbmovie.model.TMDbMovieDetail
import com.example.domain.tmdbmovie.usecases.ObserveTMDbMovieDetailUseCase
import com.example.domain.tmdbmovie.usecases.RefreshTMDbMovieDetailUseCase
import io.reactivex.subscribers.DisposableSubscriber
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class MovieDetailViewModel @Inject constructor(
    private val appCoroutineDispatchers: AppCoroutineDispatchers,
    private val observeTMDbMovieDetailUseCase: ObserveTMDbMovieDetailUseCase,
    private val refreshMovieDetailUseCase: RefreshMovieDetailUseCase,
    private val refreshTMDbMovieDetailUseCase: RefreshTMDbMovieDetailUseCase
) : ViewModel() {

    private val movieState = MutableLiveData(MovieDetailState())

    fun getState(): LiveData<MovieDetailState> = movieState

    val currentMovieDetail: String? = null

    fun handleEvent(event: MovieDetailEvent) {
        when (event) {
            is MovieDetailEvent.OnStart -> {
                observeMovieDetail(id = event.imdbID)
                refresh(event.imdbID)
                refreshOMDbBaseInformation(event.imdbID.toString())
            }
        }
    }

    private fun observeMovieDetail(id: Int) {
        observeTMDbMovieDetailUseCase.invokeUseCase(
            object : DisposableSubscriber<TMDbMovieDetail>() {
                override fun onComplete() {

                }

                override fun onNext(t: TMDbMovieDetail?) {
                    movieState.value = movieState.value!!.copy(tmDbMovieDetail = t)
                }

                override fun onError(t: Throwable?) {
                    throw Exception("Subscription failed at ${t?.localizedMessage}")
                }
            }, ObserveTMDbMovieDetailUseCase.Params(id)
        )
    }

    fun refresh(id: Int) {
        viewModelScope.launch {
            currentMovieDetail.let {
                refreshTMDbMovieDetailUseCase.invokeUseCase(
                    params = RefreshTMDbMovieDetailUseCase.Params(id)
                )
            }
        }
    }

    private fun refreshOMDbBaseInformation(imdbID: String) {
        viewModelScope.launch {
            currentMovieDetail.let {
                refreshMovieDetailUseCase.invokeUseCase(
                    params = RefreshMovieDetailUseCase.Params(imdbID)
                )
            }
        }
    }
}
