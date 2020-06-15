package com.example.remote.tmdbmovie

import com.example.data.tmdbmovie.TMDbMovieRemote
import com.example.domain.tmdbmovie.model.Result
import com.example.domain.tmdbmovie.model.TMDbMovieDetail
import com.example.remote.tmdbmovie.model.mapToDomain
import javax.inject.Inject

//All the communication with the api
//Give some information to the server (post request)
//Whatever I receive from URL, I must map to domain

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
}