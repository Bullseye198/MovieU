package com.example.domain.tmdbmovie.model

data class TMDbMovieDetail (
    val adult: Boolean, // false
    val backdropPath: String, // /fHY9TfKwC702kPxEcjkxCoLqUv6.jpg
    val belongsToCollection: Any, // null
    val budget: Int, // 0
    val genres: List<Genre>,
    val homepage: String,
    val id: Int, // 15675
    val imdbId: String, // tt0473692
    val originalLanguage: String, // en
    val originalTitle: String, // Neil Young: Heart of Gold
    val overview: String, // In March 2005, Neil Young was diagnosed with a brain aneurysm. Four days before he was scheduled for a lifesaving operation, he headed to Nashville, where he wrote and recorded the country folk album PRAIRIE WIND with old friends and family members. After the successful operation and recovery period, he returned to Nashville that August to play at the famed Ryman Auditorium, once again gathering together friends and family for this special performance.
    val popularity: Double, // 3.63
    val posterPath: String, // /bl323DpW9zUSgCwlfjTwneogXOB.jpg
    val productionCompanies: List<Any>,
    val productionCountries: List<Any>,
    val releaseDate: String, // 2006-02-10
    val revenue: Int, // 0
    val runtime: Int, // 99
    val spokenLanguages: List<SpokenLanguage>,
    val status: String, // Released
    val tagline: String,
    val title: String, // Neil Young: Heart of Gold
    val video: Boolean, // false
    val voteAverage: Double, // 7.7
    val voteCount: Int // 11
)