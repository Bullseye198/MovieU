package com.example.cache.tmdbmovies.model

import androidx.room.Entity

@Entity(
    tableName = "genre",
    primaryKeys = ["id", "genreTMDbID"]
)
data class RoomGenre(
    val id: Int,
    val genreTMDbID: String,
    val name: String
)