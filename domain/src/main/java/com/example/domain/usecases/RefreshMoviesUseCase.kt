package com.example.domain.usecases

import com.cm.base.executor.AppCoroutineDispatchers
import com.cm.base.interactors.base.CoroutineCompletableUseCase
import com.example.domain.movie.IMovieRepository
import com.example.domain.movie.model.Movie
import java.lang.Exception
import javax.inject.Inject

class RefreshMoviesUseCase @Inject constructor(
    private val iMovieRepository: IMovieRepository,
    appCoroutineDispatchers: AppCoroutineDispatchers
) : CoroutineCompletableUseCase<RefreshMoviesUseCase.Params>(appCoroutineDispatchers) {

    override suspend fun execute(params: Params?) {
        try {
            val serverMovies = iMovieRepository.fetchMovies(params!!.titleToSearchFor)
            iMovieRepository.storeMovies(serverMovies)
        } catch (e: Exception) {
        }
    }

    data class Params(
        val titleToSearchFor: String
    )
}