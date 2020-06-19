package com.example.remote.tmdbmovie.model.tvdetail


import com.example.domain.tmdbmovie.model.tvdetail.TvDetailNetwork
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class TvDetailNetworkRaw(
    @Json(name = "id")
    val id: Int, // 71
    @Json(name = "logo_path")
    val logoPath: String, // /ge9hzeaU7nMtQ4PjkFlc68dGAJ9.png
    @Json(name = "name")
    val name: String, // The CW
    @Json(name = "origin_country")
    val originCountry: String // US
)

fun TvDetailNetworkRaw.mapToDomainNetwork() = TvDetailNetwork(
    id = id,
    logoPath = logoPath,
    name = name,
    originCountry = originCountry
)