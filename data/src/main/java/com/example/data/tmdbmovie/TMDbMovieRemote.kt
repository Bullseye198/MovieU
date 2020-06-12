package com.example.data.tmdbmovie

import com.example.domain.tmdbmovie.model.Result

interface TMDbMovieRemote {

    suspend fun fetchTMDbMovies(tmdbTitleToSearchFor: String): List<Result>
}