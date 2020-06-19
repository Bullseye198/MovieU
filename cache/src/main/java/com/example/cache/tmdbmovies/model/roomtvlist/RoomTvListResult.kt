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
    val homepage: String?,
    val inProduction: Boolean?,
    val languages: List<String>?,
    val lastAirDate: String?,
    val nextEpisodeToAir: Any?,
    val numberOfEpisodes: Int?,
    val numberOfSeasons: Int?,
    val status: String?,
    val type: String?
)

fun RoomTvListResult.mapToDomainTvListResult(): TvListResult {
    return TvListResult(
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
        voteCount = voteCount
    )
}

fun RoomTvListResult.mapToDomainTMDbTvDetail(
    tvDetailCreatedBy: List<RoomTvDetailCreatedBy>,
    tvDetailgenre: List<RoomTvDetailGenre>,
    tvDetailLastEpisodeToAir: RoomTvDetailLastEpisodeToAir,
    tvDetailNetwork: List<RoomTvDetailNetwork>,
    tvDetailProductionCompany: List<RoomTvDetailProductionCompany>,
    tvDetailSeason: List<RoomTvDetailSeason>
): TMDbTvDetail {
    return TMDbTvDetail(
        id = id,
        posterPath = posterPath,
        overview = overview,
        name = name,
        languages = languages,
        voteCount = voteCount,
        voteAverage = voteAverage,
        popularity = popularity,
        originalName = originalName,
        originalLanguage = originalLanguage,
        originCountry = originCountry,
        firstAirDate = firstAirDate,
        type = type,
        homepage = homepage,
        status = status,
        backdropPath = backdropPath,
        inProduction = inProduction,
        lastAirDate = lastAirDate,
        nextEpisodeToAir = nextEpisodeToAir,
        numberOfEpisodes = numberOfEpisodes,
        numberOfSeasons = numberOfSeasons,
        tvDetailCreatedBy = tvDetailCreatedBy.map { it.mapToDomainTvDetailCreatedBy() },
        tvDetailGenres = tvDetailgenre.map { it.mapToDomainTvDetailGenre() },
        tvDetailLastEpisodeToAir = tvDetailLastEpisodeToAir.mapToDomainTvDetailLastEpisodeToAir(),
        tvDetailNetworks = tvDetailNetwork.map { it.mapToDomainTvDetailNetwork() },
        tvDetailProductionCompanies = tvDetailProductionCompany.map { it.mapToDomainTvDetailProductionCompany() },
        tvDetailSeasons = tvDetailSeason.map { it.mapToDomainTvDetailSeason() },
        episodeRunTime = emptyList()
    )
}

fun TMDbTvDetail.mapToRoomTvListResult(): RoomTvListResult {
    return RoomTvListResult(
        id = id,
        posterPath = posterPath,
        overview = overview,
        name = name,
        languages = languages,
        voteCount = voteCount,
        voteAverage = voteAverage,
        popularity = popularity,
        originalName = originalName,
        originalLanguage = originalLanguage,
        originCountry = originCountry,
        genreIds = emptyList(),
        firstAirDate = firstAirDate,
        type = type,
        homepage = homepage,
        status = status,
        backdropPath = backdropPath,
        inProduction = inProduction,
        lastAirDate = lastAirDate,
        nextEpisodeToAir = nextEpisodeToAir,
        numberOfEpisodes = numberOfEpisodes,
        numberOfSeasons = numberOfSeasons

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
        homepage = null,
        inProduction = null,
        languages = null,
        lastAirDate = null,
        nextEpisodeToAir = null,
        numberOfEpisodes = null,
        numberOfSeasons = null,
        status = null,
        type = null
    )
}
