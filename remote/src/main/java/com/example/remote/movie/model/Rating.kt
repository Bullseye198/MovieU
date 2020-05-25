package com.example.remote.movie.model


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Rating(
    @Json(name = "Source")
    val source: String, // Internet Movie Database
    @Json(name = "Value")
    val value: String // 8.4/10
)