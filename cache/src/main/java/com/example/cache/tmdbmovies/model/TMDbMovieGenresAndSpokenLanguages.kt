package com.example.cache.tmdbmovies.model

import androidx.room.Embedded
import androidx.room.Relation
import com.example.domain.tmdbmovie.model.TMDbMovieDetail

data class TMDbMovieGenresAndSpokenLanguages(
    @Embedded
    var roomTMDbMovie: TMDbCachedRoomResultFull,
    @Relation (parentColumn = "id", entityColumn = "genreTMDbID")
    var roomGenres: List<RoomGenre>,
    @Relation (parentColumn = "id", entityColumn = "spokenLanguageTMDbID")
    var roomSpokenLanguage: List<RoomSpokenLanguage>
)

fun TMDbMovieGenresAndSpokenLanguages.mapToDomainModel(): TMDbMovieDetail {
    return roomTMDbMovie.mapToDomainModelDetail(roomGenres, roomSpokenLanguage)
}
