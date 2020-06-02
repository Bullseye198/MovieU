package com.example.movieu.movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cm.base.executor.AppCoroutineDispatchers
import com.example.domain.movie.model.Movie
import com.example.domain.movie.model.MovieDetail
import com.example.domain.usecases.ObserveMovieDetailUseCase
import com.example.domain.usecases.OnGetMovieByIdUseCase
import com.example.domain.usecases.RefreshMovieDetailUseCase
import com.example.movieu.movie.moviedetail.MovieDetailEvent
import io.reactivex.subscribers.DisposableSubscriber
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.Exception
import javax.inject.Inject

class MovieDetailViewModel @Inject constructor(
    private val observeMovieDetailUseCase: ObserveMovieDetailUseCase,
    private val refreshMovieDetailUseCase: RefreshMovieDetailUseCase
) : ViewModel() {

    private val movieState = MutableLiveData<MovieDetail>()
    val movie: LiveData<MovieDetail> get() = movieState
    val imdbID: String? = null

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
        observeMovieDetailUseCase.requestMovieDetail(
            imdbID,
            object : DisposableSubscriber<MovieDetail>() {
                override fun onComplete() {

                }

                override fun onNext(t: MovieDetail?) {
                    movieState.value = t
                }

                override fun onError(t: Throwable?) {
                    throw Exception("Subscription failed at ${t?.localizedMessage}")
                }

            })
    }

    fun refresh(imdbID: String) {
        viewModelScope.launch {
            currentMovieDetail.let { refreshMovieDetailUseCase.refresh(imdbID) }
        }
    }
}
