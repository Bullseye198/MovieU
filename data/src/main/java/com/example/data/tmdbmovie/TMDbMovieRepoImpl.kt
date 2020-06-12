package com.example.data.tmdbmovie

import com.example.domain.tmdbmovie.TMDbMovieRepository
import com.example.domain.tmdbmovie.model.Result
import javax.inject.Inject

class TMDbMovieRepoImpl @Inject constructor(
    private val tmDbMovieRemote: TMDbMovieRemote
) : TMDbMovieRepository {
    override suspend fun fetchTMDbMovies(tmdbTitleToSearchFor: String): List<Result> {
        return tmDbMovieRemote.fetchTMDbMovies(tmdbTitleToSearchFor)
    }
}