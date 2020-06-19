package com.example.remote.tmdbmovie.model.tvdetail


import com.example.domain.tmdbmovie.model.tvdetail.ProductionCompany
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ProductionCompanyRaw(
    @Json(name = "id")
    val id: Int, // 1957
    @Json(name = "logo_path")
    val logoPath: String, // /nmcNfPq03WLtOyufJzQbiPu2Enc.png
    @Json(name = "name")
    val name: String, // Warner Bros. Television
    @Json(name = "origin_country")
    val originCountry: String // US
)

fun ProductionCompanyRaw.mapToDomainProductionCompany() = ProductionCompany(
    id = id,
    logoPath = logoPath,
    name = name,
    originCountry = originCountry
)