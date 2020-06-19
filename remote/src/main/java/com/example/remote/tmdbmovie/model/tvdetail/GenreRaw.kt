package com.example.remote.tmdbmovie.model.tvdetail


import com.example.domain.tmdbmovie.model.tvdetail.Genre
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class GenreRaw(
    @Json(name = "id")
    val id: Int, // 18
    @Json(name = "name")
    val name: String // Drama
)

fun GenreRaw.mapToDomainGenre() = Genre(
    id = id,
    name = name
)