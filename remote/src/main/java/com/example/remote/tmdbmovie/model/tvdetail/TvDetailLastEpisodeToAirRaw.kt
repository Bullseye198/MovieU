package com.example.remote.tmdbmovie.model.tvdetail


import com.example.domain.tmdbmovie.model.tvdetail.TvDetailLastEpisodeToAir
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class TvDetailLastEpisodeToAirRaw(
    @Json(name = "air_date")
    val airDate: String, // 2017-03-10
    @Json(name = "episode_number")
    val episodeNumber: Int, // 16
    @Json(name = "id")
    val id: Int, // 1264246
    @Json(name = "name")
    val name: String, // I Was Feeling Epic
    @Json(name = "overview")
    val overview: String, // With the fate of Mystic Falls at stake, Stefan and Damon must fight their greatest enemy for one last battle.
    @Json(name = "production_code")
    val productionCode: String, // T27.13316
    @Json(name = "season_number")
    val seasonNumber: Int, // 8
    @Json(name = "show_id")
    val showId: Int, // 18165
    @Json(name = "still_path")
    val stillPath: String, // /qxa2cuMviBp7QqcortLGinvAp4l.jpg
    @Json(name = "vote_average")
    val voteAverage: Double, // 9.6
    @Json(name = "vote_count")
    val voteCount: Int // 5
)

fun TvDetailLastEpisodeToAirRaw.mapToDomainLastEpisodeToAir() = TvDetailLastEpisodeToAir(
    airDate = airDate,
    episodeNumber = episodeNumber,
    id = id,
    name = name,
    overview = overview,
    productionCode = productionCode,
    seasonNumber = seasonNumber,
    showId = showId,
    stillPath = stillPath,
    voteAverage = voteAverage,
    voteCount = voteCount
)