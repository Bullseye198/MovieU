package com.example.data.tmdbmovie

import com.example.domain.movie.model.OMDbBaseInformation
import com.example.domain.tmdbmovie.model.moviedetail.Credits
import com.example.domain.tmdbmovie.model.movielist.Result
import com.example.domain.tmdbmovie.model.moviedetail.TMDbMovieDetail
import com.example.domain.tmdbmovie.model.tvdetail.TMDbTvDetail
import com.example.domain.tmdbmovie.model.tvlist.TvListResult
import kotlinx.coroutines.flow.Flow

interface TMDbMovieCache {

    fun observeTMDbMoviesForTitle(titleToSearchFor: String): Flow<List<Result>>

    fun observeTMDbMovieDetail(id: Int): Flow<TMDbMovieDetail>

    fun observeTMDbTvListForTitle(nameToSearchFor: String): Flow<List<TvListResult>>

    fun observeTMDbTvDetail(id: Int): Flow<TMDbTvDetail>

    suspend fun storeTMDbMovies(tmdbMovies: List<Result>)

    suspend fun storeTMDbMovieDetail(tmDbMovieDetail: TMDbMovieDetail)

    suspend fun storeTMDbCredits(credits: Credits)

    suspend fun storeTMDbTvList(tmdbTvList: List<TvListResult>)

    suspend fun storeTMDbTvDetail(tmdbTvDetail: TMDbTvDetail)

    suspend fun addOmdbInformation(omdbOMDbBaseInformation: OMDbBaseInformation)
}