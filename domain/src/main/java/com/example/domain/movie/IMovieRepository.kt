package com.example.domain.movie

import com.example.domain.movie.model.Movie
import com.example.domain.movie.model.MovieDetail
import io.reactivex.Flowable

interface IMovieRepository {

    fun observeMovies(): Flowable<List<Movie>>

    suspend fun requestMovies(titleToSearchFor: String?): List<Movie>

    suspend fun getMovieById(imdbID: String): Movie

    suspend fun fetchMovies(titleToSearchFor: String): List<Movie>

    suspend fun storeMovies(movies: List<Movie>)

    suspend fun fetchMovieDetail(imdbID: String): MovieDetail

    suspend fun storeMovieDetail(movieDetail: MovieDetail)

    fun observeMovieDetail(imdbID: String): Flowable<MovieDetail>

}