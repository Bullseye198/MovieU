package com.example.remote.tmdbmovie.model


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ResultRaw(
    @Json(name = "adult")
    val adult: Boolean, // false
    @Json(name = "backdrop_path")
    val backdropPath: String?, // /xX0IzuFa1Fj06iU2NlOmeMPe7oS.jpg
    @Json(name = "genre_ids")
    val genreIds: List<Int>,
    @Json(name = "id")
    val id: Int, // 135397
    @Json(name = "original_language")
    val originalLanguage: String, // en
    @Json(name = "original_title")
    val originalTitle: String, // Jurassic World
    @Json(name = "overview")
    val overview: String, // Twenty-two years after the events of Jurassic Park, Isla Nublar now features a fully functioning dinosaur theme park, Jurassic World, as originally envisioned by John Hammond.
    @Json(name = "popularity")
    val popularity: Double, // 30.11
    @Json(name = "poster_path")
    val posterPath: String?, // /2c0ajTi8nvrsYl5Oi1lVi6F0kd2.jpg
    @Json(name = "release_date")
    val releaseDate: String, // 2015-06-06
    @Json(name = "title")
    val title: String, // Jurassic World
    @Json(name = "video")
    val video: Boolean, // false
    @Json(name = "vote_average")
    val voteAverage: Double, // 6.6
    @Json(name = "vote_count")
    val voteCount: Int // 15655
)