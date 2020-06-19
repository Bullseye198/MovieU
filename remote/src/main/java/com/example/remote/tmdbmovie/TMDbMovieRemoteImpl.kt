package com.example.remote.tmdbmovie

import com.example.data.tmdbmovie.TMDbMovieRemote
import com.example.domain.tmdbmovie.model.Credits
import com.example.domain.tmdbmovie.model.Result
import com.example.domain.tmdbmovie.model.TMDbMovieDetail
import com.example.domain.tmdbmovie.model.tvdetail.TMDbTvDetail
import com.example.domain.tmdbmovie.model.tvlist.TvListResult
import com.example.remote.tmdbmovie.model.credits.mapToDomainCredits
import com.example.remote.tmdbmovie.model.mapToDomain
import com.example.remote.tmdbmovie.model.tvdetail.mapToDomainTMDbTvDetail
import com.example.remote.tmdbmovie.model.tvlist.mapToDomainResult
import javax.inject.Inject

class TMDbMovieRemoteImpl @Inject constructor(
    private val tmDbMovieService: TMDbMovieService
) : TMDbMovieRemote {
    override suspend fun fetchTMDbMovies(tmdbTitleToSearchFor: String): List<Result> {
        return tmDbMovieService.getCurrentMovieList(
            tmdbTitleToSearchFor = tmdbTitleToSearchFor,
            apikey = "2797198b75a6557cae56bdfdb2dd1b52"
        ).resultsRaw.map {
            Result(
                it.adult,
                it.backdropPath,
                it.genreIds,
                it.id,
                it.originalLanguage,
                it.originalTitle,
                it.overview,
                it.popularity,
                it.posterPath,
                it.releaseDate,
                it.title,
                it.video,
                it.voteAverage,
                it.voteCount
            )
        }
    }

    override suspend fun fetchTMDbMovieDetail(id: Int): TMDbMovieDetail {
        return tmDbMovieService.getTMDbMovieDetail(
            id = id,
            apikey = "2797198b75a6557cae56bdfdb2dd1b52"
        ).mapToDomain()
    }

    override suspend fun fetchTMDbCredits(id: Int): Credits {
        return tmDbMovieService.getTMDbCredits(
            id = id,
            apikey = "2797198b75a6557cae56bdfdb2dd1b52"
        ).mapToDomainCredits()
    }

    override suspend fun fetchTMDbTvList(tmdbTvToSearchFor: String): List<TvListResult> {
        return tmDbMovieService.getTMDbTvList(
            tmdbTvToSearchFor = tmdbTvToSearchFor,
            apikey = "2797198b75a6557cae56bdfdb2dd1b52"
        ).resultsRaw.map { it.mapToDomainResult() }
    }

    override suspend fun fetchTMDbTvDetail(id: Int): TMDbTvDetail {
        return tmDbMovieService.getTMDbTvDetail(
            id = id,
            apikey = "2797198b75a6557cae56bdfdb2dd1b52"
        ).mapToDomainTMDbTvDetail()
    }
}