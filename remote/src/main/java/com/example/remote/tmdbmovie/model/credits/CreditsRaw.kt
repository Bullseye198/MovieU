package com.example.remote.tmdbmovie.model.credits


import com.example.domain.tmdbmovie.model.moviedetail.Credits
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CreditsRaw(
    @Json(name = "cast")
    val cast: List<CastRaw>,
    @Json(name = "crew")
    val crew: List<CrewRaw>,
    @Json(name = "id")
    val id: Int // 603
)

fun CreditsRaw.mapToDomainCredits() =
    Credits(
        cast = cast.map { it.mapToDomain() },
        crew = crew.map { it.mapDomainCrewModel() },
        id = id
    )

