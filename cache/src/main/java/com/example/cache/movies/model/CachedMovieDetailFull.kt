package com.example.cache.movies.model

import androidx.room.Embedded
import androidx.room.Relation
import com.example.domain.movie.model.MovieDetail

data class MovieAndRatings(
    @Embedded
    var roomMovie: RoomMovie,
    @Relation(parentColumn = "id", entityColumn = "ratingsImdbID")
    var roomRatings: List<RoomRatings>
)

fun MovieAndRatings.mapToDomainModel() : MovieDetail {
    return roomMovie.mapToDomainModelDetail(roomRatings)

}
