package com.example.domain.tmdbmovie.model

import com.example.domain.tmdbmovie.model.moviedetail.Cast
import com.example.domain.tmdbmovie.model.moviedetail.Crew
import com.example.domain.tmdbmovie.model.moviedetail.Genre
import com.example.domain.tmdbmovie.model.moviedetail.SpokenLanguage
import com.example.domain.tmdbmovie.model.tvdetail.*

data class MediaDetail (
    val adult: Boolean, // false
    val backdropPath: String?, // /fHY9TfKwC702kPxEcjkxCoLqUv6.jpg
    val belongsToCollection: Any?, // null
    val budget: Int?, // 0
    val genres: List<Genre>,
    val homepage: String?,
    val id: Int, // 15675
    val imdbId: String?, // tt0473692
    val originalLanguage: String, // en
    val originalTitle: String, // Neil Young: Heart of Gold
    val overview: String, // In March 2005, Neil Young was diagnosed with a brain aneurysm. Four days before he was scheduled for a lifesaving operation, he headed to Nashville, where he wrote and recorded the country folk album PRAIRIE WIND with old friends and family members. After the successful operation and recovery period, he returned to Nashville that August to play at the famed Ryman Auditorium, once again gathering together friends and family for this special performance.
    val popularity: Double, // 3.63
    val posterPath: String?, // /bl323DpW9zUSgCwlfjTwneogXOB.jpg
    val productionCompanies: List<Any>,
    val productionCountries: List<Any>,
    val releaseDate: String, // 2006-02-10
    val revenue: Int?, // 0
    val runtime: Int?, // 99
    val spokenLanguages: List<SpokenLanguage>,
    val status: String?, // Released
    val tagline: String?,
    val title: String, // Neil Young: Heart of Gold
    val video: Boolean, // false
    val voteAverage: Double, // 7.7
    val voteCount: Int,

    //OMDb
    val imdbID: String?, // tt0133093
    val imdbRating: String?, // 8.7// 11
    val imdbVotes: String?,

    //Credits
    val cast: List<Cast>?,
    val crew: List<Crew>?,



    //Tv
    val tvDetailCreatedBy: List<TvDetailCreatedBy>,
    val firstAirDate: String, // 2009-09-10
    val tvDetailGenres: List<TvDetailGenre>,
    val inProduction: Boolean?, // false
    val languages: List<TMDbTvDetailLanguages>?,
    val lastAirDate: String?, // 2017-03-10
    val tvDetailLastEpisodeToAir: TvDetailLastEpisodeToAir,
    val name: String, // The Vampire Diaries
    val tvDetailNetworks: List<TvDetailNetwork>,
    val nextEpisodeToAir: String?, // null
    val numberOfEpisodes: Int?, // 171
    val numberOfSeasons: Int?, // 8
    val originCountry: List<String>,
    val originalName: String, // The Vampire Diaries
    val tvDetailProductionCompanies: List<TvDetailProductionCompany>,
    val tvDetailSeasons: List<TvDetailSeason>,
    val type: String? // Scripted
)