package com.example.data.tmdbmovie

import com.example.domain.tmdbmovie.model.Result
import com.example.domain.tmdbmovie.model.TMDbMovieDetail
import io.reactivex.Flowable

interface TMDbMovieCache {

    suspend fun getTMDbMovieById(id: String) : Result

    suspend fun requestTMDbMovies(titleToSearchFor: String?): List<Result>

    //suspend fun observeTMDbMovies(): Flowable<List<Result>>

    suspend fun storeTMDbMovies(tmdbMovies: List<Result>)

    suspend fun storeTMDbMovieDetail(tmDbMovieDetail: TMDbMovieDetail)

}