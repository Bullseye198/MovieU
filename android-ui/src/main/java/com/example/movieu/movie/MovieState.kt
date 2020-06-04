package com.example.movieu.movie

import com.example.domain.movie.model.Movie
import com.example.domain.movie.model.MovieDetail

data class MovieState(
    val feed: List<Movie>? = null,
    val movieDetail: MovieDetail? = null
)