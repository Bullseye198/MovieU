package com.example.remote.tmdbmovie.model.tvdetail


import com.example.domain.tmdbmovie.model.tvdetail.TvDetailGenre
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class TvDetailGenreRaw(
    @Json(name = "id")
    val id: Int, // 18
    @Json(name = "name")
    val name: String // Drama
)

fun TvDetailGenreRaw.mapToDomainGenre() = TvDetailGenre(
    id = id,
    name = name
)