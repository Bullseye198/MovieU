package com.example.remote.tmdbmovie.model.tvdetail


import com.example.domain.tmdbmovie.model.tvdetail.TvDetailProductionCompany
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class TvDetailProductionCompanyRaw(
    @Json(name = "id")
    val id: Int, // 1957
    @Json(name = "logo_path")
    val logoPath: String?, // /nmcNfPq03WLtOyufJzQbiPu2Enc.png
    @Json(name = "name")
    val name: String, // Warner Bros. Television
    @Json(name = "origin_country")
    val originCountry: String // US
)

fun TvDetailProductionCompanyRaw.mapToDomainProductionCompany() = TvDetailProductionCompany(
    id = id,
    logoPath = logoPath,
    name = name,
    originCountry = originCountry
)