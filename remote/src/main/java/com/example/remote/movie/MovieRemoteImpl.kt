package com.example.remote.movie

import com.example.data.movie.MovieRemote
import com.example.domain.movie.model.Movie
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class MovieRemoteImpl @Inject constructor(
    private val movieService: MovieService
) : MovieRemote {


    override suspend fun fetchMovies(titleToSearchFor: String): List<Movie> {
        return movieService.getCurrentMovie(
            titleToSearchFor = titleToSearchFor,
            apikey = "3e6675f5"
        ).search.map {
                Movie(it.imdbID, it.poster, it.title, it.type, it.year)
            }
    }
}