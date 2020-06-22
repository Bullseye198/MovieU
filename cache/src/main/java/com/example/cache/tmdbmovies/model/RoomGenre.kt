package com.example.cache.tmdbmovies.model

import androidx.room.Entity
import com.example.domain.tmdbmovie.model.Genre

@Entity(
    tableName = "genre",
    primaryKeys = ["id", "genreTMDbID"]
)
data class RoomGenre(
    val id: Int,
    val genreTMDbID: String,
    val name: String
)

fun Genre.mapToRoomGenre(genreTMDbID: String): RoomGenre {
    return RoomGenre(
        id = id,
        genreTMDbID = genreTMDbID,
        name = name
    )
}