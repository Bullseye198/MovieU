package com.example.data.movie

import com.example.domain.movie.model.Movie
import com.example.domain.movie.model.MovieDetail

interface MovieRemote {

    suspend fun fetchMovies(titleToSearchFor: String): List<Movie>

    suspend fun fetchMovieDetail(imdbID: String): MovieDetail
}