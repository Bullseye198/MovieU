package com.example.remote.tmdbmovie.model


import com.example.domain.tmdbmovie.model.CastList
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CastListRaw(
    @Json(name = "cast")
    val cast: List<CastRaw>,
    @Json(name = "crew")
    val crew: List<CrewRaw>,
    @Json(name = "id")
    val id: Int // 603
)

fun CastListRaw.mapToDomainCastList() = CastList(
    cast = cast.map { it.mapToDomain() },
    crew = crew.map { it.mapDomainCrewModel() },
    id = id
)

