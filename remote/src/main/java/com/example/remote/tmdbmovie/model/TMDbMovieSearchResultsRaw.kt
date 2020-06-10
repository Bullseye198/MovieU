package com.example.remote.tmdbmovie.model


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class TMDbMovieSearchResultsRaw(
    @Json(name = "page")
    val page: Int, // 1
    @Json(name = "results")
    val resultsRaw: List<ResultRaw>,
    @Json(name = "total_pages")
    val totalPages: Int, // 4
    @Json(name = "total_results")
    val totalResults: Int // 63
)