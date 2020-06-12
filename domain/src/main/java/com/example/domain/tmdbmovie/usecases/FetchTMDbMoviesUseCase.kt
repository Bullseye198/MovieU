package com.example.domain.tmdbmovie.usecases

import com.example.domain.tmdbmovie.TMDbMovieRepository
import com.example.domain.tmdbmovie.model.Result
import javax.inject.Inject

class FetchTMDbMoviesUseCase @Inject constructor(
    private val tmDbMovieRepository: TMDbMovieRepository
) {

    suspend fun fetchTMDbMovies(tmdbTitleToSearchFor: String): List<Result> {
        return tmDbMovieRepository.fetchTMDbMovies(tmdbTitleToSearchFor)
    }
}