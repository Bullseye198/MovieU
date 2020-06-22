package com.example.cache.tmdbmovies.model.roomtvdetail

import androidx.room.Entity
import com.example.domain.tmdbmovie.model.tvdetail.TMDbTvDetailLanguages

@Entity(
    tableName = "tvdetaillanguages",
    primaryKeys = ["string", "languagesID"]
)

data class RoomTvDetailLanguages (
    val string: String,
    val languagesID: String
)

fun RoomTvDetailLanguages.mapToDomainTvDetailLanguages(): TMDbTvDetailLanguages {
    return TMDbTvDetailLanguages(
        string = string
    )
}

fun TMDbTvDetailLanguages.mapToRoomTvDetailLanguages(languagesID: String): RoomTvDetailLanguages {
    return RoomTvDetailLanguages(
        string = string,
        languagesID = languagesID
    )
}