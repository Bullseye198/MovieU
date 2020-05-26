package com.example.remote.movie.model


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Movies(
    @Json(name = "imdbID")
    val imdbID: String, // tt0848228
    @Json(name = "Poster")
    val poster: String, // https://m.media-amazon.com/images/M/MV5BNDYxNjQyMjAtNTdiOS00NGYwLWFmNTAtNThmYjU5ZGI2YTI1XkEyXkFqcGdeQXVyMTMxODk2OTU@._V1_SX300.jpg
    @Json(name = "Title")
    val title: String, // The Avengers
    @Json(name = "Type")
    val type: String, // movie
    @Json(name = "Year")
    val year: String // 2012
)