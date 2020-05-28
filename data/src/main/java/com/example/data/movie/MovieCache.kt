package com.example.data.movie

import com.example.domain.movie.model.Movie
import io.reactivex.Flowable

interface MovieCache {

    suspend fun getMovieById(imdbID: String): Movie

    suspend fun requestMovies(titleToSearchFor: String?): List<Movie>

    suspend fun observeMovies(): Flowable<List<Movie>>

    suspend fun storeMovies(movies: List<Movie>)
}