package com.example.remote.movie.model


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class SearchResults(
    @Json(name = "Response")
    val response: String, // True
    @Json(name = "Search")
    val search: List<Movies>,
    @Json(name = "totalResults")
    val totalResults: String // 104
)