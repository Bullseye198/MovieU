package com.example.domain.tmdbmovie

import com.example.domain.tmdbmovie.model.Result

interface TMDbMovieRepository {

    suspend fun fetchTMDbMovies(tmdbTitleToSearchFor: String): List<Result>
}