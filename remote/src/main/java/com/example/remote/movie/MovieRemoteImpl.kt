package com.example.remote.movie

import com.example.data.movie.MovieRemote
import com.example.domain.movie.model.Movie
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class MovieRemoteImpl @Inject constructor(
    private val movieService: MovieService
): MovieRemote{


    override suspend fun fetchImages(): List<Movie> {
        TODO("Not yet implemented")
    }
}