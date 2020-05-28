package com.example.domain.usecases

import com.example.domain.movie.IMovieRepository
import java.lang.Exception
import javax.inject.Inject

class RefreshMoviesUseCase @Inject constructor(
    private val iMovieRepository: IMovieRepository
) {
    suspend fun refresh(titleToSearchFor: String) {
        try {
            val serverMovies = iMovieRepository.fetchMovies(titleToSearchFor)
            iMovieRepository.storeMovies(serverMovies)
        } catch (e: Exception) {

        }
    }
}