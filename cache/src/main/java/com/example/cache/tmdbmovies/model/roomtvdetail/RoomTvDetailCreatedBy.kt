package com.example.cache.tmdbmovies.model.roomtvdetail

import androidx.room.Entity
import com.example.domain.tmdbmovie.model.tvdetail.TvDetailCreatedBy

@Entity(
    tableName = "tvdetailcreatedBy",
    primaryKeys = ["id", "createdByID"]
)
data class RoomTvDetailCreatedBy(
    val id: Int, // 26458
    val createdByID: String,
    val creditId: String, // 52585551760ee346610970f5
    val gender: Int, // 2
    val name: String, // Kevin Williamson
    val profilePath: String? // null
)

fun RoomTvDetailCreatedBy.mapToDomainTvDetailCreatedBy(): TvDetailCreatedBy {
    return TvDetailCreatedBy(
        creditId = creditId,
        gender = gender,
        id = id,
        name = name,
        profilePath = profilePath
    )
}

fun TvDetailCreatedBy.mapToRoomTvDetailCreatedBy(createdByID: String): RoomTvDetailCreatedBy {
    return RoomTvDetailCreatedBy(
        creditId = creditId,
        gender = gender,
        id = id,
        name = name,
        profilePath = profilePath,
        createdByID = createdByID
    )
}