package com.example.cache.tmdbmovies.model.roomtvlist

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.domain.tmdbmovie.model.tvdetail.*
import com.example.domain.tmdbmovie.model.tvlist.TvListResult

@Entity(
    tableName = "tmdbTv"
)

data class RoomTvListResult(
    @PrimaryKey
    @ColumnInfo
    val id: Int,
    val backdropPath: String?,
    val firstAirDate: String,
    val genreIds: List<Int>,
    val name: String,
    val originCountry: List<String>,
    val originalLanguage: String,
    val originalName: String,
    val overview: String,
    val popularity: Double,
    val posterPath: String?,
    val voteAverage: Double,
    val voteCount: Int,

    //tvDetail
    val tvDetailCreatedBy: List<TvDetailCreatedBy>?,
    val tvDetailGenres: List<TvDetailGenre>?,
    val homepage: String?,
    val inProduction: Boolean?,
    val languages: List<String>?,
    val lastAirDate: String?,
    val tvDetailLastEpisodeToAir: TvDetailLastEpisodeToAir?,
    val tvDetailNetworks: List<TvDetailNetwork>?,
    val nextEpisodeToAir: Any?,
    val numberOfEpisodes: Int?,
    val numberOfSeasons: Int?,
    val tvDetailProductionCompanies: List<TvDetailProductionCompany>?,
    val tvDetailSeasons: List<TvDetailSeason>?,
    val status: String?,
    val type: String?
)

fun RoomTvListResult.mapToDomainTvListResult(): TvListResult {
    return TvListResult(
        backdropPath = backdropPath,
        firstAirDate = firstAirDate,
        genreIds = genreIds,
        id = id,
        name = name,
        originCountry = originCountry,
        originalLanguage = originalLanguage,
        originalName = originalName,
        overview = overview,
        popularity = popularity,
        posterPath = posterPath,
        voteAverage = voteAverage,
        voteCount = voteCount
    )
}

fun TvListResult.mapToRoomTvListResult(): RoomTvListResult {
    return RoomTvListResult(
        id = id,
        backdropPath = backdropPath,
        firstAirDate = firstAirDate,
        genreIds = genreIds,
        name = name,
        originCountry = originCountry,
        originalLanguage = originalLanguage,
        originalName = originalName,
        overview = overview,
        popularity = popularity,
        posterPath = posterPath,
        voteAverage = voteAverage,
        voteCount = voteCount,

        //tvDetail
        tvDetailCreatedBy = null,
        tvDetailGenres = null,
        homepage = null,
        inProduction = null,
        languages = null,
        lastAirDate = null,
        tvDetailLastEpisodeToAir = null,
        tvDetailNetworks = null,
        nextEpisodeToAir = null,
        numberOfEpisodes = null,
        numberOfSeasons = null,
        tvDetailProductionCompanies = null,
        tvDetailSeasons = null,
        status = null,
        type = null
    )
}
