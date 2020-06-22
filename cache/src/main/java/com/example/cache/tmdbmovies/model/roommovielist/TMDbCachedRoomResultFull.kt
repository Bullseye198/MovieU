package com.example.cache.tmdbmovies.model.roommovielist

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.cache.tmdbmovies.model.roommoviedetail.*
import com.example.domain.tmdbmovie.model.moviedetail.Genre
import com.example.domain.tmdbmovie.model.moviedetail.SpokenLanguage
import com.example.domain.tmdbmovie.model.moviedetail.TMDbMovieDetail
import com.example.domain.tmdbmovie.model.movielist.Result

@Entity(
    tableName = "tmdbMovie"
)

data class TMDbCachedRoomResultFull(
    @PrimaryKey
    @ColumnInfo
    val id: Int,
    val adult: Boolean,
    val backdropPath: String?,
    val originalLanguage: String,
    val originalTitle: String,
    val overview: String,
    val popularity: Double,
    val posterPath: String?,
    val releaseDate: String,
    val title: String,
    val video: Boolean,
    val voteAverage: Double,
    val voteCount: Int,

    //TMDb Detail
    val budget: Int?,
    val homepage: String?,
    val imdbId: String?,
    val revenue: Int?,
    val runtime: Int?,
    val status: String?,
    val tagline: String?,

    //OMDb
    val imdRating: String?,
    val imdbVotes: String?
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
    spokenLanguages: List<RoomSpokenLanguage>,
    cast: List<RoomCast>,
    crew: List<RoomCrew>
): TMDbMovieDetail {
    return TMDbMovieDetail(
        id = id,
        adult = adult,
        voteCount = voteCount,
        voteAverage = voteAverage,
        title = title,
        tagline = tagline,
        status = status,
        spokenLanguages = spokenLanguages.map {
            SpokenLanguage(
                it.iso6391,
                it.name
            )
        },
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
        genres = genres.map {
            Genre(
                it.id,
                it.name
            )
        },
        budget = budget,
        backdropPath = backdropPath,
        video = video,
        imdbVotes = imdbVotes,
        imdbRating = imdRating,
        imdbID = imdbId,
        belongsToCollection = null,
        crew = crew.map { it.mapToDomainCrew() },
        cast = cast.map { it.mapToDomainCast() }
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
        imdRating = imdbRating,
        imdbVotes = imdbVotes
    )
}

fun Result.mapToRoomModel(): TMDbCachedRoomResultFull {
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
        budget = null,
        homepage = null,
        imdbId = null,
        revenue = null,
        runtime = null,
        status = null,
        tagline = null,
        imdRating = null,
        imdbVotes = null
    )
}