package com.example.domain.movie

import com.example.domain.movie.model.Movie
import io.reactivex.Flowable

interface IMovieRepository {

    fun observeMovies(): Flowable<List<Movie>>

    suspend fun requestMovies(titleToSearchFor: String?): List<Movie>

    suspend fun getMovieById(imdbID: String): Movie

    suspend fun fetchMovies(titleToSearchFor: String): List<Movie>

    suspend fun storeMovies(movies: List<Movie>)

}