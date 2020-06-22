package com.example.cache.tmdbmovies.model.roomtvlist

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.cache.tmdbmovies.model.roomtvdetail.*
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
    val name: String,
    val originalLanguage: String,
    val originalName: String,
    val overview: String,
    val popularity: Double,
    val posterPath: String?,
    val voteAverage: Double,
    val voteCount: Int,

    //tvDetail
    val homepage: String?,
    val inProduction: Boolean?,
    val lastAirDate: String?,
    val numberOfEpisodes: Int?,
    val nextEpisodeToAir: String?,
    val numberOfSeasons: Int?,
    val status: String?,
    val type: String?
)

fun RoomTvListResult.mapToDomainTvListResult(): TvListResult {
    return TvListResult(
        id = id,
        backdropPath = backdropPath,
        firstAirDate = firstAirDate,
        genreIds = emptyList(),
        name = name,
        originalLanguage = originalLanguage,
        originalName = originalName,
        overview = overview,
        popularity = popularity,
        posterPath = posterPath,
        voteAverage = voteAverage,
        voteCount = voteCount,
        originCountry = emptyList()
    )
}

fun RoomTvListResult.mapToDomainTMDbTvDetail(
    tvDetailCreatedBy: List<RoomTvDetailCreatedBy>,
    tvDetailgenre: List<RoomTvDetailGenre>,
    tvDetailLastEpisodeToAir: RoomTvDetailLastEpisodeToAir?,
    tvDetailNetwork: List<RoomTvDetailNetwork>,
    tvDetailProductionCompany: List<RoomTvDetailProductionCompany>,
    tvDetailSeason: List<RoomTvDetailSeason>,
    tvDetailLanguages: List<RoomTvDetailLanguages>
): TMDbTvDetail {
    return TMDbTvDetail(
        id = id,
        posterPath = posterPath,
        overview = overview,
        name = name,
        voteCount = voteCount,
        voteAverage = voteAverage,
        popularity = popularity,
        originalName = originalName,
        originalLanguage = originalLanguage,
        firstAirDate = firstAirDate,
        type = type,
        homepage = homepage,
        status = status,
        backdropPath = backdropPath,
        inProduction = inProduction,
        lastAirDate = lastAirDate,
        numberOfEpisodes = numberOfEpisodes,
        numberOfSeasons = numberOfSeasons,
        nextEpisodeToAir = nextEpisodeToAir,
        originCountry = emptyList(),
        episodeRunTime = emptyList(),
        languages = tvDetailLanguages.map { it.mapToDomainTvDetailLanguages() },
        tvDetailCreatedBy = tvDetailCreatedBy.map { it.mapToDomainTvDetailCreatedBy() },
        tvDetailGenres = tvDetailgenre.map { it.mapToDomainTvDetailGenre() },
        tvDetailLastEpisodeToAir = tvDetailLastEpisodeToAir?.mapToDomainTvDetailLastEpisodeToAir(),
        tvDetailNetworks = tvDetailNetwork.map { it.mapToDomainTvDetailNetwork() },
        tvDetailProductionCompanies = tvDetailProductionCompany.map { it.mapToDomainTvDetailProductionCompany() },
        tvDetailSeasons = tvDetailSeason.map { it.mapToDomainTvDetailSeason() }
    )
}

fun TMDbTvDetail.mapToRoomTvListResult(): RoomTvListResult {
    return RoomTvListResult(
        id = id,
        posterPath = posterPath,
        overview = overview,
        name = name,
        voteCount = voteCount,
        voteAverage = voteAverage,
        popularity = popularity,
        originalName = originalName,
        originalLanguage = originalLanguage,
        firstAirDate = firstAirDate,
        type = type,
        homepage = homepage,
        status = status,
        backdropPath = backdropPath,
        inProduction = inProduction,
        lastAirDate = lastAirDate,
        numberOfEpisodes = numberOfEpisodes,
        numberOfSeasons = numberOfSeasons,
        nextEpisodeToAir = nextEpisodeToAir
    )
}

fun TvListResult.mapToRoomTvListResult(): RoomTvListResult {
    return RoomTvListResult(
        id = id,
        backdropPath = backdropPath,
        firstAirDate = firstAirDate,
        name = name,
        originalLanguage = originalLanguage,
        originalName = originalName,
        overview = overview,
        popularity = popularity,
        posterPath = posterPath,
        voteAverage = voteAverage,
        voteCount = voteCount,

        //tvDetail
        homepage = null,
        inProduction = null,
        lastAirDate = null,
        numberOfEpisodes = null,
        numberOfSeasons = null,
        status = null,
        type = null,
        nextEpisodeToAir = null
    )
}
