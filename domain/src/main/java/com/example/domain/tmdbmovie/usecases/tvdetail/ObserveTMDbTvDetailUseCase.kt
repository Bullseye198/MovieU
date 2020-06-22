package com.example.domain.tmdbmovie.usecases.tvdetail

import com.cm.base.executor.AppRxSchedulers
import com.cm.base.interactors.base.FlowableUseCase
import com.example.domain.tmdbmovie.TMDbMovieRepository
import com.example.domain.tmdbmovie.model.tvdetail.TMDbTvDetail
import io.reactivex.Flowable
import javax.inject.Inject

class ObserveTMDbTvDetailUseCase @Inject constructor(
    private val tmdbMovieRepository: TMDbMovieRepository,
    rxSchedulers: AppRxSchedulers
) : FlowableUseCase<TMDbTvDetail, ObserveTMDbTvDetailUseCase.Params>(rxSchedulers) {

    override fun buildUseCaseObservable(params: Params?): Flowable<TMDbTvDetail> {
        return tmdbMovieRepository.observeTMDbTvDetail(params!!.id)
    }

    data class Params(
        val id: Int
    )
}