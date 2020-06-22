package com.example.domain.tmdbmovie.usecases.moviedetail

import com.cm.base.executor.AppRxSchedulers
import com.cm.base.interactors.base.FlowableUseCase
import com.example.domain.tmdbmovie.TMDbMovieRepository
import com.example.domain.tmdbmovie.model.moviedetail.TMDbMovieDetail
import io.reactivex.Flowable
import javax.inject.Inject

class ObserveTMDbMovieDetailUseCase @Inject constructor(
    private val tmDbMovieRepository: TMDbMovieRepository,
    rxSchedulers: AppRxSchedulers
) : FlowableUseCase<TMDbMovieDetail, ObserveTMDbMovieDetailUseCase.Params>(rxSchedulers) {

    override fun buildUseCaseObservable(params: Params?): Flowable<TMDbMovieDetail> {
        return tmDbMovieRepository.observeTMDbMovieDetail(params!!.id)
    }

    data class Params(
        val id: Int
    )
}