package com.example.data.tmdbmovie

import com.example.domain.movie.model.OMDbBaseInformation
import com.example.domain.tmdbmovie.model.Result
import com.example.domain.tmdbmovie.model.TMDbMovieDetail
import io.reactivex.Flowable

interface TMDbMovieCache {

    suspend fun getTMDbMovieById(id: Int): Result

    suspend fun requestTMDbMovies(tmdbTitleToSearchFor: String?): List<Result>

    suspend fun observeTMDbMovies(): Flowable<List<Result>>

    suspend fun storeTMDbMovies(tmdbMovies: List<Result>)

    suspend fun storeTMDbMovieDetail(tmDbMovieDetail: TMDbMovieDetail)

    suspend fun addOmdbInformation(omdbOMDbBaseInformation: OMDbBaseInformation)

    fun observeTMDbMovieDetail(id: Int): Flowable<TMDbMovieDetail>
}