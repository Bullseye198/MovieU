package com.example.movieu.movie.moviedetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.movie.usecases.RefreshMovieDetailUseCase
import com.example.domain.tmdbmovie.model.TMDbMovieDetail
import com.example.domain.tmdbmovie.usecases.moviedetail.RefreshTMDbCreditsUseCase
import com.example.domain.tmdbmovie.usecases.moviedetail.ObserveTMDbMovieDetailUseCase
import com.example.domain.tmdbmovie.usecases.moviedetail.RefreshTMDbMovieDetailUseCase
import io.reactivex.subscribers.DisposableSubscriber
import kotlinx.coroutines.launch
import javax.inject.Inject

class MovieDetailViewModel @Inject constructor(
    private val observeTMDbMovieDetailUseCase: ObserveTMDbMovieDetailUseCase,
    private val refreshMovieDetailUseCase: RefreshMovieDetailUseCase,
    private val refreshTMDbMovieDetailUseCase: RefreshTMDbMovieDetailUseCase,
    private val refreshTMDbCreditsUseCase: RefreshTMDbCreditsUseCase
) : ViewModel() {

    private val movieState = MutableLiveData(MovieDetailState())

    fun getState(): LiveData<MovieDetailState> = movieState

    val currentMovieDetail: String? = null

    var fetchedOmdbInformation: Boolean = false

    fun handleEvent(event: MovieDetailEvent) {
        when (event) {
            is MovieDetailEvent.OnStart -> {
                observeMovieDetail(id = event.id)
                refresh(event.id)
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
                    if (t?.imdbId != null && !fetchedOmdbInformation) {
                        refreshOMDbBaseInformation(t.imdbId!!)
                        fetchedOmdbInformation = true
                    }
                }

                override fun onError(t: Throwable?) {
                    throw Exception("Subscription failed at ${t?.localizedMessage}")
                }
            }, ObserveTMDbMovieDetailUseCase.Params(id)
        )
    }

    private fun refresh(id: Int) {
        viewModelScope.launch {
            currentMovieDetail.let {
                refreshTMDbMovieDetailUseCase.invokeUseCase(
                    params = RefreshTMDbMovieDetailUseCase.Params(id = id)
                )
                refreshTMDbCreditsUseCase.invokeUseCase(
                    params = RefreshTMDbCreditsUseCase.Params(id = id)
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
