package com.example.remote.tmdbmovie.model.tvdetail


import com.example.domain.tmdbmovie.model.tvdetail.TvDetailCreatedBy
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class TvDetailCreatedByRaw(
    @Json(name = "credit_id")
    val creditId: String, // 52585551760ee346610970f5
    @Json(name = "gender")
    val gender: Int, // 2
    @Json(name = "id")
    val id: Int, // 26458
    @Json(name = "name")
    val name: String, // Kevin Williamson
    @Json(name = "profile_path")
    val profilePath: String? // null
)

fun TvDetailCreatedByRaw.mapToDomainCreatedBy() = TvDetailCreatedBy(
    creditId = creditId,
    gender = gender,
    id = id,
    name = name,
    profilePath = profilePath
)