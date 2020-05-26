package com.example.data.movie

import com.example.domain.movie.IMovieRepository
import com.example.domain.movie.model.Movie
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MovieRepoImpl @Inject constructor(
    private val movieRemote: MovieRemote
) : IMovieRepository {

    override suspend fun getMovies(): List<Movie> {
        return movieRemote.fetchImages()
    }
}