package com.example.data.tmdbmovie

import com.example.domain.movie.model.OMDbBaseInformation
import com.example.domain.tmdbmovie.model.Result
import com.example.domain.tmdbmovie.model.TMDbMovieDetail
import io.reactivex.Flowable

interface TMDbMovieCache {

    fun observeTMDbMovies(): Flowable<List<Result>>

    fun observeTMDbMovieDetail(id: Int): Flowable<TMDbMovieDetail>

    suspend fun storeTMDbMovies(tmdbMovies: List<Result>)

    suspend fun storeTMDbMovieDetail(tmDbMovieDetail: TMDbMovieDetail)

    suspend fun addOmdbInformation(omdbOMDbBaseInformation: OMDbBaseInformation)
}