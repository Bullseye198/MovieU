package com.example.cache.movies

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.domain.movie.model.Movie

@Entity(
    tableName = "movie"
)
data class RoomMovie(
    @PrimaryKey
    @ColumnInfo(name = "id")
    val imdbID: String,
    val poster: String,
    val title: String,
    val type: String,
    val year: String
)

fun RoomMovie.mapToDomainModel(): Movie {
    return Movie(
        imdbID = imdbID,
        poster = poster,
        title = title,
        type = type,
        year = year
    )
}

fun Movie.mapToRoomModel(): RoomMovie {
    return RoomMovie(
        imdbID = imdbID,
        poster = poster,
        title = title,
        type = type,
        year = year
    )
}
