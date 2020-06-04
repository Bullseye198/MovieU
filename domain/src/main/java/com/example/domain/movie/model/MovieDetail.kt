package com.example.domain.movie.model

data class MovieDetail(
    val actors: String?, // Keanu Reeves, Laurence Fishburne, Carrie-Anne Moss, Hugo Weaving
    val awards: String?, // Won 4 Oscars. Another 37 wins & 50 nominations.
    val boxOffice: String?, // N/A
    val country: String?, // USA
    val dVD: String?, // N/A
    val director: String?, // Lana Wachowski, Lilly Wachowski
    val genre: String?, // Action, Sci-Fi
    val imdbID: String, // tt0133093
    val imdbRating: String?, // 8.7
    val imdbVotes: String?, // 1,608,954
    val language: String?, // English
    val metascore: String?, // 73
    val plot: String?, // A computer hacker learns from mysterious rebels about the true nature of his reality and his role in the war against its controllers.
    val poster: String, // https://m.media-amazon.com/images/M/MV5BNzQzOTk3OTAtNDQ0Zi00ZTVkLWI0MTEtMDllZjNkYzNjNTc4L2ltYWdlXkEyXkFqcGdeQXVyNjU0OTQ0OTY@._V1_SX300.jpg
    val production: String?, // N/A
    val rated: String?, // R
    val ratings: List<Rating>?,
    val released: String?, // 31 Mar 1999
    val response: String?, // True
    val runtime: String?, // 136 min
    val title: String, // The Matrix
    val type: String, // movie
    val website: String?, // N/A
    val writer: String?, // Lilly Wachowski, Lana Wachowski
    val year: String // 1999
)
