package com.example.domain.tmdbmovie.usecases.movielist

import com.cm.base.executor.AppCoroutineDispatchers
import com.cm.base.interactors.base.CoroutineCompletableUseCase
import com.example.domain.tmdbmovie.TMDbMovieRepository
import javax.inject.Inject

class RefreshTMDbMoviesUseCase @Inject constructor(
    private val tmDbMovieRepository: TMDbMovieRepository,
    appCoroutineDispatchers: AppCoroutineDispatchers
) : CoroutineCompletableUseCase<RefreshTMDbMoviesUseCase.Params>(appCoroutineDispatchers) {

    override suspend fun execute(params: Params?) {
        try {
            val tmdbServerMovies = tmDbMovieRepository.fetchTMDbMovies(params!!.id)
            tmDbMovieRepository.storeTMDbMovies(tmdbServerMovies)
        } catch (e: Exception) {
        }
    }

    data class Params(
        val id: String
    )
}