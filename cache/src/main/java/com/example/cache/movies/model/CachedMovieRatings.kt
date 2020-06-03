package com.example.cache.movies.model

import androidx.room.Entity

@Entity(
    tableName = "ratings",
    primaryKeys = ["source", "ratingsImdbID"]
)
data class RoomRatings(
    val source: String,
    val ratingsImdbID: String,
    val value: String
)