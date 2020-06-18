package com.example.domain.tmdbmovie.usecases

import com.cm.base.executor.AppCoroutineDispatchers
import com.cm.base.interactors.base.CoroutineCompletableUseCase
import com.example.domain.tmdbmovie.TMDbMovieRepository
import javax.inject.Inject

class RefreshTMDbCreditsUseCase @Inject constructor(
    private val tmDbMovieRepository: TMDbMovieRepository,
    appCoroutineDispatchers: AppCoroutineDispatchers
) : CoroutineCompletableUseCase<RefreshTMDbCreditsUseCase.Params>(appCoroutineDispatchers) {

    override suspend fun execute(params: Params?) {
        try {
            val serverCredits = tmDbMovieRepository.fetchTMDbCredits(params!!.id)
            tmDbMovieRepository.storeTMDbCredits(serverCredits)
        } catch (e: Exception) {
        }
    }

    data class Params(
        val id: Int
    )
}