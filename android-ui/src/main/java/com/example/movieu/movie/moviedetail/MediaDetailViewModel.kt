package com.example.movieu.movie.moviedetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.movie.usecases.RefreshMovieDetailUseCase
import com.example.domain.tmdbmovie.model.moviedetail.TMDbMovieDetail
import com.example.domain.tmdbmovie.model.tvdetail.TMDbTvDetail
import com.example.domain.tmdbmovie.usecases.moviedetail.RefreshTMDbCreditsUseCase
import com.example.domain.tmdbmovie.usecases.moviedetail.ObserveTMDbMovieDetailUseCase
import com.example.domain.tmdbmovie.usecases.moviedetail.RefreshTMDbMovieDetailUseCase
import com.example.domain.tmdbmovie.usecases.tvdetail.ObserveTMDbTvDetailUseCase
import com.example.domain.tmdbmovie.usecases.tvdetail.RefreshTMDbTvDetailUseCase
import com.example.movieu.movie.tvdetail.TvDetailState
import io.reactivex.subscribers.DisposableSubscriber
import kotlinx.coroutines.launch
import javax.inject.Inject

class MediaDetailViewModel @Inject constructor(
    private val observeTMDbMovieDetailUseCase: ObserveTMDbMovieDetailUseCase,
    private val observeTMDbTvDetailUseCase: ObserveTMDbTvDetailUseCase,
    private val refreshMovieDetailUseCase: RefreshMovieDetailUseCase,
    private val refreshTMDbMovieDetailUseCase: RefreshTMDbMovieDetailUseCase,
    private val refreshTMDbCreditsUseCase: RefreshTMDbCreditsUseCase,
    private val refreshTMDbTvDetailUseCase: RefreshTMDbTvDetailUseCase
) : ViewModel() {

    private val movieState = MutableLiveData(MovieDetailState())
    fun getMovieState(): LiveData<MovieDetailState> = movieState

    private val tvState = MutableLiveData(TvDetailState())
    fun getTvState(): LiveData<TvDetailState> = tvState

    val currentMovieDetail: String? = null

    var fetchedOmdbInformation: Boolean = false

    fun handleEvent(event: MovieDetailEvent) {
        when (event) {
            is MovieDetailEvent.OnStart -> {
                observeMovieDetail(id = event.id)
                observeTMDbTvDetail(id = event.id)
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

    private fun observeTMDbTvDetail(id: Int) {
        observeTMDbTvDetailUseCase.invokeUseCase(
            object : DisposableSubscriber<TMDbTvDetail>() {
                override fun onComplete() {
                }

                override fun onNext(t: TMDbTvDetail?) {
                    tvState.value = tvState.value!!.copy(tmbTvDetail = t)
                }

                override fun onError(t: Throwable?) {
                    throw Exception("Subscription failed at ${t?.localizedMessage}")
                }
            }, ObserveTMDbTvDetailUseCase.Params(id)
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
                refreshTMDbTvDetailUseCase.invokeUseCase(
                    params = RefreshTMDbTvDetailUseCase.Params(id = id)
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
