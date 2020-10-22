package com.example.movieu.movie.medialist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.tmdbmovie.usecases.ObserveMediaSearchUseCase
import com.example.domain.tmdbmovie.usecases.movielist.RefreshTMDbMoviesUseCase
import com.example.domain.tmdbmovie.usecases.tvlist.RefreshTMDbTvListUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@ExperimentalCoroutinesApi
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
        viewModelScope.launch {
            observeMediaSearchUseCase.buildStream(
                ObserveMediaSearchUseCase.Params(
                    currentMedia
                )
            )
                .catch { }
                .collect { TMDbMedia ->
                    mediaState.value = mediaState.value!!.copy(feed = TMDbMedia)
                }
        }
    }

    private fun refreshTMDbMediaAndUpdate() {
        viewModelScope.launch {
            currentMedia.let {
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
}