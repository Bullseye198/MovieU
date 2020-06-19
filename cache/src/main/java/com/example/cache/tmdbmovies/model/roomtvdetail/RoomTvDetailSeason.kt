package com.example.cache.tmdbmovies.model.roomtvdetail

import androidx.room.Entity
import com.example.domain.tmdbmovie.model.tvdetail.TvDetailSeason

@Entity(
    tableName = "tvdetailseason",
    primaryKeys = ["id", "seasonID"]
)
data class RoomTvDetailSeason(
    val id: Int, // 28881,
    val seasonID: String,
    val airDate: String, // 2009-08-25
    val episodeCount: Int, // 5
    val name: String, // Specials
    val overview: String,
    val posterPath: String, // /qJ3IvwDNG1hFIXRNhv9JXTCaszg.jpg
    val seasonNumber: Int // 0
)

fun RoomTvDetailSeason.mapToDomainTvDetailSeason(): RoomTvDetailSeason {
    return RoomTvDetailSeason(
        airDate = airDate,
        seasonID = seasonID,
        episodeCount = episodeCount,
        id = id,
        name = name,
        overview = overview,
        posterPath = posterPath,
        seasonNumber = seasonNumber
    )
}

fun TvDetailSeason.mapToRoomTvDetailSeason(seasonID: String): RoomTvDetailSeason {
    return RoomTvDetailSeason(
        airDate = airDate,
        episodeCount = episodeCount,
        id = id,
        name = name,
        overview = overview,
        posterPath = posterPath,
        seasonNumber = seasonNumber,
        seasonID = seasonID
    )
}