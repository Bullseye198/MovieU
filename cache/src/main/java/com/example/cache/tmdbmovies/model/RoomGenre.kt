package com.example.cache.tmdbmovies.model

import androidx.room.Entity
import com.example.domain.tmdbmovie.model.moviedetail.Genre

@Entity(
    tableName = "genre",
    primaryKeys = ["id", "genreTMDbID"]
)
data class RoomGenre(
    val id: Int,
    val genreTMDbID: Int,
    val name: String
)

fun Genre.mapToRoomGenre(genreTMDbID: Int): RoomGenre {
    return RoomGenre(
        id = id,
        genreTMDbID = genreTMDbID,
        name = name
    )
}