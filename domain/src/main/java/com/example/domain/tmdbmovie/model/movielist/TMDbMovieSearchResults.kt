package com.example.domain.tmdbmovie.model.movielist

import com.example.domain.tmdbmovie.model.movielist.Result

data class TMDbMovieSearchResults(
    val page: Int, // 1
    val resultsRaw: List<Result>,
    val totalPages: Int, // 4
    val totalResults: Int // 63
)