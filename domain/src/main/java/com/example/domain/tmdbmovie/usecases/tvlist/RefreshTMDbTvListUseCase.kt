package com.example.domain.tmdbmovie.usecases.tvlist

import com.cm.base.executor.AppCoroutineDispatchers
import com.cm.base.interactors.base.CoroutineCompletableUseCase
import com.example.domain.tmdbmovie.TMDbMovieRepository
import javax.inject.Inject

class RefreshTMDbTvListUseCase @Inject constructor(
    private val tmDbMovieRepository: TMDbMovieRepository,
    appCoroutineDispatchers: AppCoroutineDispatchers
) : CoroutineCompletableUseCase<RefreshTMDbTvListUseCase.Params>(appCoroutineDispatchers) {

    override suspend fun execute(params: Params?) {
        try {
            val tmdbTvServerList = tmDbMovieRepository.fetchTMDbTvList(params!!.tmdbTvToSearchFor)
            tmDbMovieRepository.storeTMDbTvList(tmdbTvServerList)
        } catch (e: Exception) {
        }
    }

    data class Params(
        val tmdbTvToSearchFor: String
    )
}