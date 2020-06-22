package com.example.cache.tmdbmovies.model

import androidx.room.Entity
import com.example.domain.tmdbmovie.model.SpokenLanguage

@Entity(
    tableName = "spokenLanguage",
    primaryKeys = ["iso6391", "spokenLanguageTMDbID"]
)

data class RoomSpokenLanguage(
val iso6391: String,
val spokenLanguageTMDbID: Int,
val name: String
)

fun SpokenLanguage.mapToRoomSpokenLanguage(spokenLanguageTMDbID: Int): RoomSpokenLanguage {
    return RoomSpokenLanguage(
        iso6391 = iso6391,
        spokenLanguageTMDbID = spokenLanguageTMDbID,
        name = name
    )
}