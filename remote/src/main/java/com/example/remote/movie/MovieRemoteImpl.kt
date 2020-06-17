package com.example.remote.movie

import com.example.data.movie.MovieRemote
import com.example.domain.movie.model.OMDbBaseInformation
import com.example.remote.movie.model.mapToDomain
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MovieRemoteImpl @Inject constructor(
    private val movieService: MovieService
) : MovieRemote {
    override suspend fun fetchMovieDetail(imdbID: String): OMDbBaseInformation {
        return movieService.getMovieDetail(
            imdbID = imdbID,
            apikey = "3e6675f5"
        ).mapToDomain()
    }
}