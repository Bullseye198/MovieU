package com.example.cache.tmdbmovies.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.domain.tmdbmovie.model.Genre
import com.example.domain.tmdbmovie.model.Result
import com.example.domain.tmdbmovie.model.SpokenLanguage
import com.example.domain.tmdbmovie.model.TMDbMovieDetail

@Entity(
    tableName = "tmdbMovie"
)

data class TMDbCachedRoomResultFull(
    @PrimaryKey
    @ColumnInfo
    val id: Int,
    val adult: Boolean,
    val backdropPath: String,
    //val genreIds: List<Int>,
    val originalLanguage: String,
    val originalTitle: String,
    val overview: String,
    val popularity: Double,
    val posterPath: String,
    val releaseDate: String,
    val title: String,
    val video: Boolean,
    val voteAverage: Double,
    val voteCount: Int,

    val belongsToCollection: Any?,
    val budget: Int?,
    val homepage: String?,
    val imdbId: String?,
    //val productionCompanies: List<Any>,
    //val productionCountries: List<Any>,
    val revenue: Int?,
    val runtime: Int?,
    val status: String?,
    val tagline: String?

)

fun TMDbCachedRoomResultFull.mapToDomainModelList(): Result {
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

fun TMDbCachedRoomResultFull.mapToDomainModelDetail(
    genres: List<RoomGenre>,
    spokenLanguages: List<RoomSpokenLanguage>
): TMDbMovieDetail {
    return TMDbMovieDetail(
        id = id,
        adult = adult,
        voteCount = voteCount,
        voteAverage = voteAverage,
        title = title,
        tagline = tagline,
        status = status,
        spokenLanguages = spokenLanguages.map { SpokenLanguage(it.iso6391, it.name) },
        runtime = runtime,
        revenue = revenue,
        releaseDate = releaseDate,
        productionCountries = emptyList(),
        productionCompanies = emptyList(),
        posterPath = posterPath,
        popularity = popularity,
        overview = overview,
        originalTitle = originalTitle,
        originalLanguage = originalLanguage,
        imdbId = imdbId,
        homepage = homepage,
        genres = genres.map { Genre(it.id, it.name) },
        budget = budget,
        belongsToCollection = belongsToCollection,
        backdropPath = backdropPath,
        video = video
    )
}

fun TMDbMovieDetail.mapToFullRoomModel(): TMDbCachedRoomResultFull {
    return TMDbCachedRoomResultFull(
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
        tagline = tagline,
        status = status,
        runtime = runtime,
        revenue = revenue,
        imdbId = imdbId,
        homepage = homepage,
        budget = budget,
        belongsToCollection = belongsToCollection
        // genreIds = emptyList()
    )
}

fun Result.mapToRoomModel(): TMDbCachedRoomResultFull {
    return TMDbCachedRoomResultFull(
        id = id,
        adult = adult,
        backdropPath = backdropPath,
        originalLanguage = originalLanguage,
        //genreIds = emptyList(),
        originalTitle = originalTitle,
        overview = overview,
        popularity = popularity,
        posterPath = posterPath,
        releaseDate = releaseDate,
        title = title,
        video = video,
        voteAverage = voteAverage,
        voteCount = voteCount,
        belongsToCollection = null,
        budget = null,
        homepage = null,
        imdbId = null,
        revenue = null,
        runtime = null,
        status = null,
        tagline = null
    )
}