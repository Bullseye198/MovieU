package com.example.domain.usecases

import com.cm.base.executor.AppRxSchedulers
import com.example.domain.movie.IMovieRepository
import com.example.domain.movie.model.MovieDetail
import io.reactivex.subscribers.DisposableSubscriber
import javax.inject.Inject

class ObserveMovieDetailUseCase @Inject constructor(
    private val iMovieRepository: IMovieRepository,
    private val rxSchedulers: AppRxSchedulers
) {
    fun requestMovieDetail(
        imdbID: String,
        subscriber: DisposableSubscriber<MovieDetail>
    ) {
        iMovieRepository.observeMovieDetail(imdbID = imdbID)
            .subscribeOn(rxSchedulers.io)
            .observeOn(rxSchedulers.main)
            .subscribeWith(subscriber)
    }
}