package com.example.movieu.movie.moviedetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.movie.model.OMDbBaseInformation
import com.example.domain.movie.usecases.ObserveMovieDetailUseCase
import com.example.domain.movie.usecases.RefreshMovieDetailUseCase
import io.reactivex.subscribers.DisposableSubscriber
import kotlinx.coroutines.launch
import javax.inject.Inject

class MovieDetailViewModel @Inject constructor(
    private val observeMovieDetailUseCase: ObserveMovieDetailUseCase,
    private val refreshMovieDetailUseCase: RefreshMovieDetailUseCase
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

    private fun observeMovieDetail(imdbID: String) {
        observeMovieDetailUseCase.invokeUseCase(
            object : DisposableSubscriber<OMDbBaseInformation>() {
                override fun onComplete() {

                }

                override fun onNext(t: OMDbBaseInformation?) {
                    movieState.value = movieState.value!!.copy(OMDbBaseInformation = t)
                }

                override fun onError(t: Throwable?) {
                    throw Exception("Subscription failed at ${t?.localizedMessage}")
                }
            }, ObserveMovieDetailUseCase.Params(imdbID))
    }

    fun refresh(imdbID: String) {
        viewModelScope.launch {
            currentMovieDetail.let { refreshMovieDetailUseCase.invokeUseCase(params = RefreshMovieDetailUseCase.Params(imdbID)) }
        }
    }
}
