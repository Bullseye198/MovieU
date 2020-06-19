package com.example.domain.tmdbmovie.model.tvlist

data class TMDbTvList(
    val page: Int, // 1
    val tvListResults: List<TvListResult>,
    val totalPages: Int, // 1
    val totalResults: Int // 1
)