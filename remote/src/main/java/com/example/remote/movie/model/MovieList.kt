package com.example.remote.movie.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class MovieList(
    @Json(name = "movies")
    val movies: List<Movies>
)