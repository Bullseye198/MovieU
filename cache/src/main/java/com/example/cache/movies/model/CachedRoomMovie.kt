package com.example.cache.movies.model

import androidx.room.*
import com.example.domain.movie.model.Movie
import com.example.domain.movie.model.MovieDetail
import com.example.domain.movie.model.Rating

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
    val year: String,
    val actors: String?,
    val awards: String?,
    val boxOffice: String?,
    val country: String?,
    val dVD: String?,
    val director: String?,
    val genre: String?,
    val imdbRating: String?,
    val imdbVotes: String?,
    val language: String?,
    val metascore: String?,
    val plot: String?,
    val production: String?,
    val rated: String?,
    val released: String?,
    val response: String?,
    val runtime: String?,
    val website: String?,
    val writer: String?
)

fun RoomMovie.mapToDomainModelList(): Movie {
    return Movie(
        imdbID = imdbID,
        poster = poster,
        title = title,
        type = type,
        year = year
    )
}

fun RoomMovie.mapToDomainModelDetail(ratings: List<RoomRatings>): MovieDetail {
    return MovieDetail(
        imdbID = imdbID,
        poster = poster,
        title = title,
        type = type,
        year = year,
        actors = actors,
        awards = awards,
        boxOffice = boxOffice,
        country = country,
        dVD = dVD,
        director = director,
        genre = genre,
        imdbRating = imdbRating,
        imdbVotes = imdbVotes,
        language = language,
        metascore = metascore,
        plot = plot,
        production = production,
        rated = rated,
        released = released,
        response = response,
        runtime = runtime,
        website = website,
        writer = writer,
        ratings = ratings.map { Rating(it.source, it.value) }
    )
}

fun MovieDetail.mapToFullRoomModel(): RoomMovie {
    return RoomMovie(
        imdbID = imdbID,
        poster = poster,
        title = title,
        type = type,
        year = year,
        actors = actors,
        awards = awards,
        boxOffice = boxOffice,
        country = country,
        dVD = dVD,
        director = director,
        genre = genre,
        imdbRating = imdbRating,
        imdbVotes = imdbVotes,
        language = language,
        metascore = metascore,
        plot = plot,
        production = production,
        rated = rated,
        released = released,
        response = response,
        runtime = runtime,
        website = website,
        writer = writer
    )
}

fun Movie.mapToRoomModel(): RoomMovie {
    return RoomMovie(
        imdbID = imdbID,
        poster = poster,
        title = title,
        type = type,
        year = year,
        actors = null,
        awards = null,
        boxOffice = null,
        country = null,
        dVD = null,
        director = null,
        genre = null,
        imdbRating = null,
        imdbVotes = null,
        language = null,
        metascore = null,
        plot = null,
        production = null,
        rated = null,
        released = null,
        response = null,
        runtime = null,
        website = null,
        writer = null
    )
}
