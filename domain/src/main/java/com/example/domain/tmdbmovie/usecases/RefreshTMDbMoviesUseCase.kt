package com.example.domain.tmdbmovie.usecases

import com.example.domain.tmdbmovie.TMDbMovieRepository
import javax.inject.Inject


class RefreshTMDbMoviesUseCase @Inject constructor(
    private val tmDbMovieRepository: TMDbMovieRepository
){

    suspend fun refresh(tmdbTitleToSearchFor: String) {
        try {
            val tmdbServerMovies = tmDbMovieRepository.fetchTMDbMovies(tmdbTitleToSearchFor)
            tmDbMovieRepository.storeTMDbMovies(tmdbServerMovies)
        } catch (e: Exception) {
        }
    }
}