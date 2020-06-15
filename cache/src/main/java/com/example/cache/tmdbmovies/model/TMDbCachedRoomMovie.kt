package com.example.cache.tmdbmovies.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.domain.tmdbmovie.model.Result

@Entity(
    tableName = "tmdbMovie"
)

data class TMDbCachedRoomMovie(
    @PrimaryKey
    @ColumnInfo
    val id: Int,
    val adult: Boolean,
    val backdropPath: String?,
    // val genreIds: List<Int>,
    val originalLanguage: String,
    val originalTitle: String,
    val overview: String,
    val popularity: Double,
    val posterPath: String?,
    val releaseDate: String,
    val title: String,
    val video: Boolean,
    val voteAverage: Double,
    val voteCount: Int
)

fun TMDbCachedRoomMovie.mapToDomainModel(): Result {
    return Result(
        id = id,
        adult = adult,
        backdropPath = backdropPath,
        originalLanguage = originalLanguage,
        originalTitle = originalTitle,
        overview = overview,
        popularity = popularity,
        posterPath = posterPath,
        releaseDate = releaseDate,
        title = title,
        video = video,
        voteAverage = voteAverage,
        voteCount = voteCount,
        genreIds = emptyList()
    )
}

fun Result.mapToRoomModel(): TMDbCachedRoomMovie {
    return TMDbCachedRoomMovie(
        id = id,
        adult = adult,
        backdropPath = backdropPath,
        originalLanguage = originalLanguage,
        originalTitle = originalTitle,
        overview = overview,
        popularity = popularity,
        posterPath = posterPath,
        releaseDate = releaseDate,
        title = title,
        video = video,
        voteAverage = voteAverage,
        voteCount = voteCount
    )
}