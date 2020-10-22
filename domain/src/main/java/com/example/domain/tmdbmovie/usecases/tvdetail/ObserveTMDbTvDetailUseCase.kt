package com.example.domain.tmdbmovie.usecases.tvdetail

import com.cm.base.executor.AppCoroutineDispatchers
import com.cm.base.interactors.base.FlowUseCase
import com.example.domain.tmdbmovie.TMDbMovieRepository
import com.example.domain.tmdbmovie.model.tvdetail.TMDbTvDetail
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ObserveTMDbTvDetailUseCase @Inject constructor(
    private val tmdbMovieRepository: TMDbMovieRepository,
    appCoroutineDispatchers: AppCoroutineDispatchers
) : FlowUseCase<TMDbTvDetail, ObserveTMDbTvDetailUseCase.Params>(appCoroutineDispatchers) {

    override fun buildStream(params: Params?): Flow<TMDbTvDetail> {
        return tmdbMovieRepository.observeTMDbTvDetail(params!!.id)
    }

    data class Params(
        val id: Int
    )
}