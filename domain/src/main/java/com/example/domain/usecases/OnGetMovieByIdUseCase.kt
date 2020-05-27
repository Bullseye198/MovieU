package com.example.domain.usecases

import com.cm.base.executor.AppCoroutineDispatchers
import com.example.domain.movie.IMovieRepository
import com.example.domain.movie.model.Movie
import javax.inject.Inject

class OnGetMovieByIdUseCase @Inject constructor(
    private val iMovieRepository: IMovieRepository,
    val appDispatchers: AppCoroutineDispatchers
) {

    suspend fun getMovie(imdbID: String): Movie {
        return iMovieRepository.getMovieById(imdbID)
    }
}