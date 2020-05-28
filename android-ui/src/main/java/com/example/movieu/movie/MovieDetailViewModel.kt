package com.example.movieu.movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cm.base.executor.AppCoroutineDispatchers
import com.example.domain.movie.model.Movie
import com.example.domain.usecases.OnGetMovieByIdUseCase
import com.example.movieu.movie.moviedetail.MovieDetailEvent
import io.reactivex.subscribers.DisposableSubscriber
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class MovieDetailViewModel @Inject constructor(
    private val onGetMovieByIdUseCase: OnGetMovieByIdUseCase,
    private val appCoroutineDispatchers: AppCoroutineDispatchers
) : ViewModel() {

    private val movieState = MutableLiveData<Movie>()
    val movie: LiveData<Movie> get() = movieState


    fun handleEvent(event: MovieDetailEvent) {
        when (event) {
            is MovieDetailEvent.OnStart -> getMovie(event.imdbID)
        }
    }

    private fun getMovie(imdbID: String) {

        onGetMovieByIdUseCase.getMovie(object: DisposableSubscriber<Movie>() {
            override fun onComplete() {

            }

            override fun onNext(t: Movie?) {
                movieState.value = t
            }

            override fun onError(t: Throwable?) {
                throw Exception("Subscription failed because ${t?.localizedMessage}.")
            }

        }, imdbID)

        }
    }
