package com.example.remote.tmdbmovie.model


import com.example.domain.tmdbmovie.model.moviedetail.Genre
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class GenreRaw(
    @Json(name = "id")
    val id: Int, // 99
    @Json(name = "name")
    val name: String // Documentary
)

fun GenreRaw.mapDomainGenresModel() =
    Genre(
        id = id,
        name = name
    )