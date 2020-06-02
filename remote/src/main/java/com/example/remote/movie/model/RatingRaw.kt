package com.example.remote.movie.model


import com.example.domain.movie.model.Rating
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class RatingRaw(
    @Json(name = "Source")
    val source: String, // Internet Movie Database
    @Json(name = "Value")
    val value: String // 8.7/10
)

fun RatingRaw.mapDomainRatingModel() = Rating(
    source = source,
    value = value
)