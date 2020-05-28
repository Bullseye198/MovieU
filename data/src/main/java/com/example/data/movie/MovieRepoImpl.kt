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

    override suspend fun getMovieById(imdbID: String): Movie {
        return movieCache.getMovieById(imdbID)
    }

    override suspend fun requestMovies(titleToSearchFor: String?): List<Movie> {
        return movieCache.requestMovies(titleToSearchFor)
    }

    override suspend fun fetchMovies(titleToSearchFor: String): List<Movie> {
        return movieRemote.fetchMovies(titleToSearchFor)
    }

    override suspend fun storeMovies(movies: List<Movie>) {
        return movieCache.storeMovies(movies)
    }
}