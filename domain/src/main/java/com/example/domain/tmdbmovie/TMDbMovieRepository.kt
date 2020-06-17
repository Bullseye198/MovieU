package com.example.domain.tmdbmovie

import com.example.domain.movie.model.OMDbBaseInformation
import com.example.domain.tmdbmovie.model.Result
import com.example.domain.tmdbmovie.model.TMDbMovieDetail
import io.reactivex.Flowable

interface TMDbMovieRepository {

    fun observeTMDbMovies(): Flowable<List<Result>>

    fun observeTMDbMovieDetail(id: Int): Flowable<TMDbMovieDetail>

    suspend fun requestTMDbMovies(tmdbTitleToSearchFor: String?): List<Result>

    suspend fun getTMDbMovieById(id: Int): Result

    suspend fun fetchTMDbMovies(tmdbTitleToSearchFor: String): List<Result>

    suspend fun storeTMDbMovies(tmdbMovies: List<Result>)

    suspend fun fetchTMDbMovieDetail(id: Int): TMDbMovieDetail

    suspend fun addOmdbInformation(omdbOMDbBaseInformation: OMDbBaseInformation)

    suspend fun storeTMDbMovieDetail(tmDbMovieDetail: TMDbMovieDetail)
}