package com.example.domain.tmdbmovie.usecases.tvdetail

import com.cm.base.executor.AppCoroutineDispatchers
import com.cm.base.interactors.base.CoroutineCompletableUseCase
import com.example.domain.tmdbmovie.TMDbMovieRepository
import java.lang.Exception
import javax.inject.Inject

class RefreshTMDbTvDetailUseCase @Inject constructor(
    private val tmDbMovieRepository: TMDbMovieRepository,
    appCoroutineDispatchers: AppCoroutineDispatchers
) : CoroutineCompletableUseCase<RefreshTMDbTvDetailUseCase.Params>(appCoroutineDispatchers) {

    override suspend fun execute(params: Params?) {
        try {
            val tmdbTvServerDetail = tmDbMovieRepository.fetchTMDbTvDetail(params!!.id)
            tmDbMovieRepository.storeTMDbTvDetail(tmdbTvServerDetail)
        } catch (e: Exception) {
        }
    }

    data class Params(
        val id: Int
    )
}