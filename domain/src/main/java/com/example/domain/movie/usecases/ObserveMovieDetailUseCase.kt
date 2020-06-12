package com.example.domain.movie.usecases

import com.cm.base.executor.AppRxSchedulers
import com.cm.base.interactors.base.FlowableUseCase
import com.example.domain.movie.IMovieRepository
import com.example.domain.movie.model.MovieDetail
import io.reactivex.Flowable
import javax.inject.Inject

class ObserveMovieDetailUseCase @Inject constructor(
    private val iMovieRepository: IMovieRepository,
    rxSchedulers: AppRxSchedulers
) : FlowableUseCase<MovieDetail, ObserveMovieDetailUseCase.Params>(rxSchedulers) {

    override fun buildUseCaseObservable(params: Params?): Flowable<MovieDetail> {
        return iMovieRepository.observeMovieDetail(params!!.imdbID)
    }

    data class Params(
        val imdbID: String
    )
}