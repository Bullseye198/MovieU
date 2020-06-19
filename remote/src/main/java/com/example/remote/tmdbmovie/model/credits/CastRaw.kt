package com.example.remote.tmdbmovie.model.credits


import com.example.domain.tmdbmovie.model.Cast
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CastRaw(
    @Json(name = "cast_id")
    val castId: Int, // 34
    @Json(name = "character")
    val character: String, // Thomas A. Anderson / Neo
    @Json(name = "credit_id")
    val creditId: String, // 52fe425bc3a36847f80181c1
    @Json(name = "gender")
    val gender: Int, // 2
    @Json(name = "id")
    val id: Int, // 6384
    @Json(name = "name")
    val name: String, // Keanu Reeves
    @Json(name = "order")
    val order: Int, // 0
    @Json(name = "profile_path")
    val profilePath: String? // /d9HyjGMCt4wgJIOxAGlaYWhKsiN.jpg
)

fun CastRaw.mapToDomain() = Cast(
    castId = castId,
    character = character,
    creditId = creditId,
    gender = gender,
    id = id,
    name = name,
    order = order,
    profilePath = profilePath
)