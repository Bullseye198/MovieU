package com.example.remote.movie.model


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Movies(
    @Json(name = "Actors")
    val actors: String, // Robert Downey Jr., Chris Evans, Mark Ruffalo, Chris Hemsworth
    @Json(name = "Awards")
    val awards: String, // Nominated for 1 Oscar. Another 64 wins & 104 nominations.
    @Json(name = "BoxOffice")
    val boxOffice: String, // N/A
    @Json(name = "Country")
    val country: String, // USA
    @Json(name = "DVD")
    val dVD: String, // N/A
    @Json(name = "Director")
    val director: String, // Anthony Russo, Joe Russo
    @Json(name = "Genre")
    val genre: String, // Action, Adventure, Drama, Sci-Fi
    @Json(name = "imdbID")
    val imdbID: String, // tt4154796
    @Json(name = "imdbRating")
    val imdbRating: String, // 8.4
    @Json(name = "imdbVotes")
    val imdbVotes: String, // 716,526
    @Json(name = "Language")
    val language: String, // English, Japanese, Xhosa, German
    @Json(name = "Metascore")
    val metascore: String, // 78
    @Json(name = "Plot")
    val plot: String, // After the devastating events of Avengers: Infinity War (2018), the universe is in ruins. With the help of remaining allies, the Avengers assemble once more in order to reverse Thanos' actions and restore balance to the universe.
    @Json(name = "Poster")
    val poster: String, // https://m.media-amazon.com/images/M/MV5BMTc5MDE2ODcwNV5BMl5BanBnXkFtZTgwMzI2NzQ2NzM@._V1_SX300.jpg
    @Json(name = "Production")
    val production: String, // N/A
    @Json(name = "Rated")
    val rated: String, // PG-13
    @Json(name = "Ratings")
    val ratings: List<Rating>,
    @Json(name = "Released")
    val released: String, // 26 Apr 2019
    @Json(name = "Response")
    val response: String, // True
    @Json(name = "Runtime")
    val runtime: String, // 181 min
    @Json(name = "Title")
    val title: String, // Avengers: Endgame
    @Json(name = "Type")
    val type: String, // movie
    @Json(name = "Website")
    val website: String, // N/A
    @Json(name = "Writer")
    val writer: String, // Christopher Markus (screenplay by), Stephen McFeely (screenplay by), Stan Lee (based on the Marvel comics by), Jack Kirby (based on the Marvel comics by), Joe Simon (Captain America created by), Jack Kirby (Captain America created by), Steve Englehart (Star-Lord created by), Steve Gan (Star-Lord created by), Bill Mantlo (Rocket Raccoon created by), Keith Giffen (Rocket Raccoon created by), Jim Starlin (Thanos, Gamora & Drax created by), Stan Lee (Groot created by), Larry Lieber (Groot created by), Jack Kirby (Groot created by), Steve Englehart (Mantis created by), Don Heck (Mantis created by)
    @Json(name = "Year")
    val year: String // 2019
)