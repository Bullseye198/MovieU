package com.example.domain.tmdbmovie

import com.example.domain.movie.model.OMDbBaseInformation
import com.example.domain.tmdbmovie.model.Credits
import com.example.domain.tmdbmovie.model.Result
import com.example.domain.tmdbmovie.model.TMDbMovieDetail
import io.reactivex.Flowable

interface TMDbMovieRepository {

    fun observeTMDbMovies(): Flowable<List<Result>>

    fun observeTMDbMovieDetail(id: Int): Flowable<TMDbMovieDetail>

    fun observeTMDbCredits(id: Int): Flowable<Credits>

    suspend fun fetchTMDbMovies(tmdbTitleToSearchFor: String): List<Result>

    suspend fun fetchTMDbMovieDetail(id: Int): TMDbMovieDetail

    suspend fun fetchTMDbCredits(id: Int): Credits

    suspend fun storeTMDbMovies(tmdbMovies: List<Result>)

    suspend fun storeTMDbMovieDetail(tmDbMovieDetail: TMDbMovieDetail)

    suspend fun storeTMDbCredits(credits: Credits)

    suspend fun addOmdbInformation(omdbOMDbBaseInformation: OMDbBaseInformation)
}