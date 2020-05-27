package com.example.domain.movie

import com.example.domain.movie.model.Movie
import io.reactivex.Flowable

interface IMovieRepository {

    //fun observeImages(): Flowable<List<Movie>>

    suspend fun requestMovies(): List<Movie>

    suspend fun getMovieById(imdbID: String): Movie

    suspend fun fetchMovies(): List<Movie>

    suspend fun storeMovies(movies: List<Movie>)

}