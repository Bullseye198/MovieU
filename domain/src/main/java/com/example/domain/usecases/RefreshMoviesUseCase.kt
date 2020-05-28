package com.example.domain.usecases

import com.example.domain.movie.IMovieRepository
import java.lang.Exception
import javax.inject.Inject

class RefreshMoviesUseCase @Inject constructor(
    private val iMovieRepository: IMovieRepository
) {
    suspend fun refresh() {
        try {
            val serverMovies = iMovieRepository.fetchMovies()
            iMovieRepository.storeMovies(serverMovies)
        } catch (e: Exception) {

        }
    }
}