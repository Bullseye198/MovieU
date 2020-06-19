package com.example.remote.tmdbmovie.model.tvlist


import com.example.domain.tmdbmovie.model.tvlist.Result
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ResultRaw(
    @Json(name = "backdrop_path")
    val backdropPath: String, // /1qVOS9wcChP1Bt4Dnnxrb25aSJa.jpg
    @Json(name = "first_air_date")
    val firstAirDate: String, // 2009-09-10
    @Json(name = "genre_ids")
    val genreIds: List<Int>,
    @Json(name = "id")
    val id: Int, // 18165
    @Json(name = "name")
    val name: String, // The Vampire Diaries
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
    @Json(name = "vote_average")
    val voteAverage: Double, // 8.1
    @Json(name = "vote_count")
    val voteCount: Int // 2139
)

fun ResultRaw.mapToDomainResult() = Result(
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