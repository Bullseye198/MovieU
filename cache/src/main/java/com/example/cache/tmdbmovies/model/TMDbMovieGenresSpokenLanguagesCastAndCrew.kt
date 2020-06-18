package com.example.cache.tmdbmovies.model

import androidx.room.Embedded
import androidx.room.Relation
import com.example.domain.tmdbmovie.model.TMDbMovieDetail

data class TMDbMovieGenresSpokenLanguagesCastAndCrew(
    @Embedded
    var roomTMDbMovie: TMDbCachedRoomResultFull,
    @Relation(parentColumn = "id", entityColumn = "genreTMDbID")
    var roomGenres: List<RoomGenre>,
    @Relation(parentColumn = "id", entityColumn = "spokenLanguageTMDbID")
    var roomSpokenLanguage: List<RoomSpokenLanguage>,
    @Relation(parentColumn = "id", entityColumn = "castTMDbID")
    var roomCast: List<RoomCast>,
    @Relation(parentColumn = "id", entityColumn = "crewTMDbID")
    var roomCrew: List<RoomCrew>
)

fun TMDbMovieGenresSpokenLanguagesCastAndCrew.mapToDomainModel(): TMDbMovieDetail {
    return roomTMDbMovie.mapToDomainModelDetail(roomGenres, roomSpokenLanguage, roomCast, roomCrew)
}
