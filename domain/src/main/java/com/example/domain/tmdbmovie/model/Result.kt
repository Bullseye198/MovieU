package com.example.domain.tmdbmovie.model

data class Result(
    val adult: Boolean, // false
    val backdropPath: String?, // /xX0IzuFa1Fj06iU2NlOmeMPe7oS.jpg
    val genreIds: List<Int>,
    val id: Int, // 135397
    val originalLanguage: String, // en
    val originalTitle: String, // Jurassic World
    val overview: String, // Twenty-two years after the events of Jurassic Park, Isla Nublar now features a fully functioning dinosaur theme park, Jurassic World, as originally envisioned by John Hammond.
    val popularity: Double, // 30.11
    val posterPath: String?, // /2c0ajTi8nvrsYl5Oi1lVi6F0kd2.jpg
    val releaseDate: String, // 2015-06-06
    val title: String, // Jurassic World
    val video: Boolean, // false
    val voteAverage: Double, // 6.6
    val voteCount: Int // 15655
)