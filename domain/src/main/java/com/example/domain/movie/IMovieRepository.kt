package com.example.domain.movie

import com.example.domain.movie.model.Movie

interface IMovieRepository {

    suspend fun getMovieById(imdbID: String): Movie

    suspend fun getMovies(): List<Movie>

}