package com.example.remote.tmdbmovie.model.tvdetail


import com.example.domain.tmdbmovie.model.tvdetail.Season
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class SeasonRaw(
    @Json(name = "air_date")
    val airDate: String, // 2009-08-25
    @Json(name = "episode_count")
    val episodeCount: Int, // 5
    @Json(name = "id")
    val id: Int, // 28881
    @Json(name = "name")
    val name: String, // Specials
    @Json(name = "overview")
    val overview: String,
    @Json(name = "poster_path")
    val posterPath: String, // /qJ3IvwDNG1hFIXRNhv9JXTCaszg.jpg
    @Json(name = "season_number")
    val seasonNumber: Int // 0
)

fun SeasonRaw.mapToDomainSeason() = Season(
    airDate = airDate,
    episodeCount = episodeCount,
    id = id,
    name = name,
    overview = overview,
    posterPath = posterPath,
    seasonNumber = seasonNumber
)