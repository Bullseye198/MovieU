package com.example.cache.tmdbmovies.model.roomtvdetail

import androidx.room.Entity
import com.example.domain.tmdbmovie.model.tvdetail.TvDetailGenre

@Entity(
    tableName = "tvdetailgenre",
    primaryKeys = ["id", "genreID"]
)

class RoomTvDetailGenre(
    val id: Int, // 18,
    val genreID: String,
    val name: String // Drama){}
)

fun RoomTvDetailGenre.mapToDomainTvDetailGenre(): TvDetailGenre {
    return TvDetailGenre(
        id = id,
        name = name
    )
}

fun TvDetailGenre.mapToRoomTvDetailGenre(genreID: String): RoomTvDetailGenre {
    return RoomTvDetailGenre(
        id = id,
        name = name,
        genreID = genreID
    )
}