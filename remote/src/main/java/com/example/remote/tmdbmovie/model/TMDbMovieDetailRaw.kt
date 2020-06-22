package com.example.remote.tmdbmovie.model


import com.example.domain.tmdbmovie.model.moviedetail.TMDbMovieDetail
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class TMDbMovieDetailRaw(
    @Json(name = "adult")
    val adult: Boolean, // false
    @Json(name = "backdrop_path")
    val backdropPath: String, // /fHY9TfKwC702kPxEcjkxCoLqUv6.jpg
    @Json(name = "belongs_to_collection")
    val belongsToCollection: Any, // null
    @Json(name = "budget")
    val budget: Int, // 0
    @Json(name = "genres")
    val genres: List<GenreRaw>,
    @Json(name = "homepage")
    val homepage: String,
    @Json(name = "id")
    val id: Int, // 15675
    @Json(name = "imdb_id")
    val imdbId: String, // tt0473692
    @Json(name = "original_language")
    val originalLanguage: String, // en
    @Json(name = "original_title")
    val originalTitle: String, // Neil Young: Heart of Gold
    @Json(name = "overview")
    val overview: String, // In March 2005, Neil Young was diagnosed with a brain aneurysm. Four days before he was scheduled for a lifesaving operation, he headed to Nashville, where he wrote and recorded the country folk album PRAIRIE WIND with old friends and family members. After the successful operation and recovery period, he returned to Nashville that August to play at the famed Ryman Auditorium, once again gathering together friends and family for this special performance.
    @Json(name = "popularity")
    val popularity: Double, // 3.63
    @Json(name = "poster_path")
    val posterPath: String, // /bl323DpW9zUSgCwlfjTwneogXOB.jpg
    @Json(name = "production_companies")
    val productionCompanies: List<Any>,
    @Json(name = "production_countries")
    val productionCountries: List<Any>,
    @Json(name = "release_date")
    val releaseDate: String, // 2006-02-10
    @Json(name = "revenue")
    val revenue: Int, // 0
    @Json(name = "runtime")
    val runtime: Int, // 99
    @Json(name = "spoken_languages")
    val spokenLanguages: List<SpokenLanguageRaw>,
    @Json(name = "status")
    val status: String, // Released
    @Json(name = "tagline")
    val tagline: String,
    @Json(name = "title")
    val title: String, // Neil Young: Heart of Gold
    @Json(name = "video")
    val video: Boolean, // false
    @Json(name = "vote_average")
    val voteAverage: Double, // 7.7
    @Json(name = "vote_count")
    val voteCount: Int // 11
)

fun TMDbMovieDetailRaw.mapToDomain() =
    TMDbMovieDetail(
        adult = adult,
        backdropPath = backdropPath,
        belongsToCollection = belongsToCollection,
        budget = budget,
        genres = genres.map { it.mapDomainGenresModel() },
        homepage = homepage,
        id = id,
        imdbId = imdbId,
        originalLanguage = originalLanguage,
        originalTitle = originalTitle,
        overview = overview,
        popularity = popularity,
        posterPath = posterPath,
        productionCompanies = productionCompanies,
        productionCountries = productionCountries,
        releaseDate = releaseDate,
        revenue = revenue,
        runtime = runtime,
        spokenLanguages = spokenLanguages.map { it.domainSpokenLanguagesModel() },
        status = status,
        tagline = tagline,
        title = title,
        video = video,
        voteAverage = voteAverage,
        voteCount = voteCount,
        imdbVotes = null,
        imdbRating = null,
        imdbID = null,
        cast = null,
        crew = null
    )