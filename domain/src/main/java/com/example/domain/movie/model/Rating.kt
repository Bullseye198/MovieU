package com.example.domain.movie.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

data class Rating (
val source: String, // Internet Movie Database
val value: String // 8.7/10
)