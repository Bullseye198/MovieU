package com.example.movieu.movie.movielist

import com.example.domain.movie.model.Movie

data class MovieListState(
    val feed: List<Movie>? = null
    )