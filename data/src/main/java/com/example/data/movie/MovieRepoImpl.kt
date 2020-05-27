package com.example.data.movie

import com.example.domain.movie.IMovieRepository
import com.example.domain.movie.model.Movie
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MovieRepoImpl @Inject constructor(
    private val movieCache: MovieCache,
    private val movieRemote: MovieRemote
) : IMovieRepository {
    override suspend fun requestMovies(): List<Movie> {
        return movieCache.requestMovies()
    }

    override suspend fun getMovieById(imdbID: String): Movie {
        return movieRemote.fetchMovies()
            .first { it.imdbID  == imdbID}
    }

    override suspend fun fetchMovies(): List<Movie> {
        return movieRemote.fetchMovies()
    }

    override suspend fun storeMovies(movies: List<Movie>) {
        return movieCache.storeMovies(movies)
    }


}