package com.example.data.movie

import com.example.domain.movie.IMovieRepository
import com.example.domain.movie.model.OMDbBaseInformation
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MovieRepoImpl @Inject constructor(
    private val movieRemote: MovieRemote
) : IMovieRepository {

    override suspend fun fetchMovieDetail(imdbID: String): OMDbBaseInformation {
        return movieRemote.fetchMovieDetail(imdbID)
    }
}