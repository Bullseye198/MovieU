package com.example.domain.tmdbmovie

import com.example.domain.tmdbmovie.model.Result

interface TMDbMovieRepository {

    suspend fun getTMDbMovieById(id: String) : Result

    suspend fun requestTMDbMovies(tmdbTitleToSearchFor: String?): List<Result>

    //suspend fun observeTMDbMovies(): Flowable<List<Result>>

    suspend fun storeTMDbMovies(tmdbMovies: List<Result>)

    suspend fun fetchTMDbMovies(tmdbTitleToSearchFor: String): List<Result>
}