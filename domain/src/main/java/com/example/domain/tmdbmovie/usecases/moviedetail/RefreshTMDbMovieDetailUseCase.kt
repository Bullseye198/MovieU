package com.example.domain.tmdbmovie.usecases.moviedetail

import com.cm.base.executor.AppCoroutineDispatchers
import com.cm.base.interactors.base.CoroutineCompletableUseCase
import com.example.domain.tmdbmovie.TMDbMovieRepository
import java.lang.Exception
import javax.inject.Inject

class RefreshTMDbMovieDetailUseCase @Inject constructor(
    private val tmDbMovieRepository: TMDbMovieRepository,
    appCoroutineDispatchers: AppCoroutineDispatchers
) : CoroutineCompletableUseCase<RefreshTMDbMovieDetailUseCase.Params>(appCoroutineDispatchers) {

    override suspend fun execute(params: Params?) {
        try {
            val serverMovieDetail = tmDbMovieRepository.fetchTMDbMovieDetail(params!!.id)
            tmDbMovieRepository.storeTMDbMovieDetail(serverMovieDetail)
        } catch (e: Exception) {
            val x = e
        }
    }

    data class Params(
        val id: Int
    )
}