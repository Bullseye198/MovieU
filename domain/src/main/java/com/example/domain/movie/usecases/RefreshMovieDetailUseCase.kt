package com.example.domain.movie.usecases

import com.cm.base.executor.AppCoroutineDispatchers
import com.cm.base.interactors.base.CoroutineCompletableUseCase
import com.example.domain.movie.IMovieRepository
import com.example.domain.tmdbmovie.TMDbMovieRepository
import java.lang.Exception
import javax.inject.Inject

class RefreshMovieDetailUseCase @Inject constructor(
    private val iMovieRepository: IMovieRepository,
    private val tmDbMovieRepository: TMDbMovieRepository,
    appCoroutineDispatchers: AppCoroutineDispatchers
) : CoroutineCompletableUseCase<RefreshMovieDetailUseCase.Params>(appCoroutineDispatchers) {

    override suspend fun execute(params: Params?) {
        try {
            val serverMovieDetail = iMovieRepository.fetchMovieDetail(params!!.imdbID)
            tmDbMovieRepository.addOmdbInformation(serverMovieDetail)
        } catch (e: Exception) {
        }
    }

    data class Params(
        val imdbID: String
    )

}