package com.example.movieu.movie.moviedetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.movie.usecases.RefreshMovieDetailUseCase
import com.example.domain.tmdbmovie.model.TMDbMovieDetail
import com.example.domain.tmdbmovie.usecases.ObserveTMDbMovieDetailUseCase
import com.example.domain.tmdbmovie.usecases.RefreshTMDbMovieDetailUseCase
import io.reactivex.subscribers.DisposableSubscriber
import kotlinx.coroutines.launch
import javax.inject.Inject

class MovieDetailViewModel @Inject constructor(
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
                observeMovieDetail(imdbID = event.imdbID)
                refresh(event.imdbID)
            }
        }
    }

    private fun observeMovieDetail(imdbID: Int) {
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
            }, ObserveTMDbMovieDetailUseCase.Params(imdbID)
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
}
