package com.example.movieu.movie.movielist

import com.example.domain.tmdbmovie.model.movielist.Result

data class MovieListState(
    val feed: List<Result>? = null
    )