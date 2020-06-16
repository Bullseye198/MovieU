package com.example.remote.movie.model


import com.example.domain.movie.model.OMDbBaseInformation
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class OMDbMovieDetailRaw(
    @Json(name = "Actors")
    val actors: String, // Keanu Reeves, Laurence Fishburne, Carrie-Anne Moss, Hugo Weaving
    @Json(name = "Awards")
    val awards: String, // Won 4 Oscars. Another 37 wins & 50 nominations.
    @Json(name = "BoxOffice")
    val boxOffice: String, // N/A
    @Json(name = "Country")
    val country: String, // USA
    @Json(name = "DVD")
    val dVD: String, // N/A
    @Json(name = "Director")
    val director: String, // Lana Wachowski, Lilly Wachowski
    @Json(name = "Genre")
    val genre: String, // Action, Sci-Fi
    @Json(name = "imdbID")
    val imdbID: String, // tt0133093
    @Json(name = "imdbRating")
    val imdbRating: String, // 8.7
    @Json(name = "imdbVotes")
    val imdbVotes: String, // 1,608,954
    @Json(name = "Language")
    val language: String, // English
    @Json(name = "Metascore")
    val metascore: String, // 73
    @Json(name = "Plot")
    val plot: String, // A computer hacker learns from mysterious rebels about the true nature of his reality and his role in the war against its controllers.
    @Json(name = "Poster")
    val poster: String, // https://m.media-amazon.com/images/M/MV5BNzQzOTk3OTAtNDQ0Zi00ZTVkLWI0MTEtMDllZjNkYzNjNTc4L2ltYWdlXkEyXkFqcGdeQXVyNjU0OTQ0OTY@._V1_SX300.jpg
    @Json(name = "Production")
    val production: String, // N/A
    @Json(name = "Rated")
    val rated: String, // R
    @Json(name = "Ratings")
    val ratings: List<RatingRaw>,
    @Json(name = "Released")
    val released: String, // 31 Mar 1999
    @Json(name = "Response")
    val response: String, // True
    @Json(name = "Runtime")
    val runtime: String, // 136 min
    @Json(name = "Title")
    val title: String, // The Matrix
    @Json(name = "Type")
    val type: String, // movie
    @Json(name = "Website")
    val website: String, // N/A
    @Json(name = "Writer")
    val writer: String, // Lilly Wachowski, Lana Wachowski
    @Json(name = "Year")
    val year: String // 1999
)

fun OMDbMovieDetailRaw.mapToDomain() = OMDbBaseInformation(
    imdbID = imdbID,
    imdbRating = imdbRating,
    imdbVotes = imdbVotes
)
