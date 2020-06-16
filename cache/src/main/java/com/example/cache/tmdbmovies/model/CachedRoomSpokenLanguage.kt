package com.example.cache.tmdbmovies.model

import androidx.room.Entity

@Entity(
    tableName = "spokenLanguage",
    primaryKeys = ["iso6391", "spokenLanguageTMDbID"]
)

data class RoomSpokenLanguage(
val iso6391: String,
val spokenLanguageTMDbID: String,
val name: String
)