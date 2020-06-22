package com.example.cache.tmdbmovies.model

import androidx.room.Entity
import com.example.domain.tmdbmovie.model.Cast

@Entity(
    tableName = "cast",
    primaryKeys = ["id", "castTMDbID"]
)

data class RoomCast (
    val castId: Int, // 34
    val castTMDbID: Int,
    val character: String, // Thomas A. Anderson / Neo
    val creditId: String, // 52fe425bc3a36847f80181c1
    val gender: Int, // 2
    val id: Int, // 6384
    val name: String, // Keanu Reeves
    val order: Int, // 0
    val profilePath: String? // /d9HyjGMCt4wgJIOxAGlaYWhKsiN.jpg
)

fun RoomCast.mapToDomainCast(): Cast{
    return Cast(
        castId = castId,
        character = character,
        creditId = creditId,
        gender = gender,
        id = id,
        name = name,
        order = order,
        profilePath = profilePath
    )
}

fun Cast.mapToRoomCast(castTMDbID: Int): RoomCast{
    return RoomCast(
        castId = castId,
        character = character,
        creditId = creditId,
        gender = gender,
        id = id,
        name = name,
        order = order,
        profilePath = profilePath,
        castTMDbID = castTMDbID
    )
}