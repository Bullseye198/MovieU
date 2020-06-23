package com.example.movieu.movie.media

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.tmdbmovie.model.MediaList
import com.example.domain.tmdbmovie.usecases.ObserveMediaSearchUseCase
import com.example.domain.tmdbmovie.usecases.movielist.RefreshTMDbMoviesUseCase
import com.example.domain.tmdbmovie.usecases.tvlist.RefreshTMDbTvListUseCase
import io.reactivex.subscribers.DisposableSubscriber
import kotlinx.coroutines.launch
import javax.inject.Inject

class MediaListViewModel @Inject constructor(
    private val refreshTMDbMoviesUseCase: RefreshTMDbMoviesUseCase,
    private val refreshTMDbTvListUseCase: RefreshTMDbTvListUseCase,
    private val observeMediaSearchUseCase: ObserveMediaSearchUseCase
) : ViewModel() {

    var currentMedia: String = ""

    private val mediaState = MutableLiveData(MediaListState())

    fun getState(): LiveData<MediaListState> = mediaState

    init {
        observeTMDbMedia()
    }

    fun onNewMediaSearched(newMedia: String) {
        this.currentMedia = newMedia
        observeMediaSearchUseCase.onSearchTermChanged(newMedia)
        refreshTMDbMediaAndUpdate()
        observeTMDbMedia()
    }

    private fun observeTMDbMedia() {
        observeMediaSearchUseCase.invokeUseCase(
            object : DisposableSubscriber<List<MediaList>>() {
                override fun onComplete() {

                }

                override fun onNext(t: List<MediaList>?) {
                    mediaState.value = mediaState.value!!.copy(feed = t)
                }

                override fun onError(t: Throwable?) {
                    throw Exception("Subscription failed at ${t?.localizedMessage}")
                }

            },
            params = ObserveMediaSearchUseCase.Params(
                mediaToSearchFor = currentMedia
            )
        )
    }

    private fun refreshTMDbMediaAndUpdate() {
        viewModelScope.launch {
            currentMedia?.let {
                refreshTMDbMoviesUseCase.invokeUseCase(
                    params = RefreshTMDbMoviesUseCase.Params(
                        it
                    )
                )
                refreshTMDbTvListUseCase.invokeUseCase(
                    params = RefreshTMDbTvListUseCase.Params(
                        it
                    )
                )
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        observeMediaSearchUseCase.dispose()
    }
}