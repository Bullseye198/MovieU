package com.example.cache.tmdbmovies.model.roomtvdetail

import androidx.room.Entity
import com.example.domain.tmdbmovie.model.tvdetail.TvDetailLastEpisodeToAir

@Entity(
    tableName = "tvdetaillastepisodetoair",
    primaryKeys = ["id", "lastEpisodeToAirID"]
)

data class RoomTvDetailLastEpisodeToAir (
    val id: Int, // 1264246
    val lastEpisodeToAirID: String,
    val airDate: String, // 2017-03-10
    val episodeNumber: Int, // 16
    val name: String, // I Was Feeling Epic
    val overview: String, // With the fate of Mystic Falls at stake, Stefan and Damon must fight their greatest enemy for one last battle.
    val productionCode: String, // T27.13316
    val seasonNumber: Int, // 8
    val showId: Int, // 18165
    val stillPath: String, // /qxa2cuMviBp7QqcortLGinvAp4l.jpg
    val voteAverage: Double, // 9.6
    val voteCount: Int // 5
)

fun RoomTvDetailLastEpisodeToAir.mapToDomainTvDetailLastEpisodeToAir(): TvDetailLastEpisodeToAir {
    return TvDetailLastEpisodeToAir(
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
}

fun TvDetailLastEpisodeToAir.mapToRoomTvDetailLastEpisodeToAir(lastEpisodeToAirID: String): RoomTvDetailLastEpisodeToAir {
    return RoomTvDetailLastEpisodeToAir(
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
        voteCount = voteCount,
        lastEpisodeToAirID = lastEpisodeToAirID
    )
}