package com.example.remote.tmdbmovie.model.tvdetail


import com.example.domain.tmdbmovie.model.tvdetail.TMDbTvDetail
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class TMDbTvDetailRaw(
    @Json(name = "backdrop_path")
    val backdropPath: String, // /1qVOS9wcChP1Bt4Dnnxrb25aSJa.jpg
    @Json(name = "created_by")
    val tvDetailCreatedBy: List<TvDetailCreatedByRaw>,
    @Json(name = "episode_run_time")
    val episodeRunTime: List<Int>,
    @Json(name = "first_air_date")
    val firstAirDate: String, // 2009-09-10
    @Json(name = "genres")
    val tvDetailGenres: List<TvDetailGenreRaw>,
    @Json(name = "homepage")
    val homepage: String, // http://www.cwtv.com/shows/the-vampire-diaries
    @Json(name = "id")
    val id: Int, // 18165
    @Json(name = "in_production")
    val inProduction: Boolean, // false
    @Json(name = "languages")
    val languages: List<String>,
    @Json(name = "last_air_date")
    val lastAirDate: String, // 2017-03-10
    @Json(name = "last_episode_to_air")
    val tvDetailLastEpisodeToAir: TvDetailLastEpisodeToAirRaw,
    @Json(name = "name")
    val name: String, // The Vampire Diaries
    @Json(name = "networks")
    val tvDetailNetworks: List<TvDetailNetworkRaw>,
    @Json(name = "next_episode_to_air")
    val nextEpisodeToAir: Any, // null
    @Json(name = "number_of_episodes")
    val numberOfEpisodes: Int, // 171
    @Json(name = "number_of_seasons")
    val numberOfSeasons: Int, // 8
    @Json(name = "origin_country")
    val originCountry: List<String>,
    @Json(name = "original_language")
    val originalLanguage: String, // en
    @Json(name = "original_name")
    val originalName: String, // The Vampire Diaries
    @Json(name = "overview")
    val overview: String, // The story of two vampire brothers obsessed with the same girl, who bears a striking resemblance to the beautiful but ruthless vampire they knew and loved in 1864.
    @Json(name = "popularity")
    val popularity: Double, // 65.142
    @Json(name = "poster_path")
    val posterPath: String, // /aBkVgChtyyJaHyZh1gfd8DbzQon.jpg
    @Json(name = "production_companies")
    val tvDetailProductionCompanies: List<TvDetailProductionCompanyRaw>,
    @Json(name = "seasons")
    val TvDetailseasonsRaw: List<TvDetailSeasonRaw>,
    @Json(name = "status")
    val status: String, // Ended
    @Json(name = "type")
    val type: String, // Scripted
    @Json(name = "vote_average")
    val voteAverage: Double, // 8.1
    @Json(name = "vote_count")
    val voteCount: Int // 2145
)

fun TMDbTvDetailRaw.mapToDomainTMDbTvDetail() = TMDbTvDetail(
     backdropPath = backdropPath,
     tvDetailCreatedBy = tvDetailCreatedBy.map { it.mapToDomainCreatedBy() },
     episodeRunTime = episodeRunTime,
     firstAirDate = firstAirDate,
     tvDetailGenres = tvDetailGenres.map { it.mapToDomainGenre() },
     homepage = homepage,
     id = id,
     inProduction = inProduction,
     languages = languages,
     lastAirDate = lastAirDate,
     tvDetailLastEpisodeToAir = tvDetailLastEpisodeToAir.mapToDomainLastEpisodeToAir(),
     name = name,
     tvDetailNetworks = tvDetailNetworks.map { it.mapToDomainNetwork() },
     nextEpisodeToAir = nextEpisodeToAir,
     numberOfEpisodes = numberOfEpisodes,
     numberOfSeasons = numberOfSeasons,
     originCountry = originCountry,
     originalLanguage = originalLanguage,
     originalName = originalName,
     overview = overview,
     popularity = popularity,
     posterPath = posterPath,
     tvDetailProductionCompanies = tvDetailProductionCompanies.map { it.mapToDomainProductionCompany() },
     tvDetailSeasons = TvDetailseasonsRaw.map { it.mapToDomainSeason() },
     status = status,
     type = type,
     voteAverage = voteAverage,
     voteCount = voteCount
)