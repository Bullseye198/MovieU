package com.example.remote.tmdbmovie

import com.example.data.tmdbmovie.TMDbMovieRemote
import com.example.domain.tmdbmovie.model.Cast
import com.example.domain.tmdbmovie.model.Result
import com.example.domain.tmdbmovie.model.TMDbMovieDetail
import com.example.remote.tmdbmovie.model.mapToDomain
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

    override suspend fun fetchTMDbCast(id: Int): List<Cast> {
        return tmDbMovieService.getTMDbMovieCast(
            id = id,
            apikey = "2797198b75a6557cae56bdfdb2dd1b52"
        ).cast.map {
            Cast(
                it.castId,
                it.character,
                it.creditId,
                it.gender,
                it.id,
                it.name,
                it.order,
                it.profilePath
            )
        }
    }
}