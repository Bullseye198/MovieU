package com.example.remote.movie

import com.example.data.movie.MovieRemote
import com.example.domain.movie.model.Movie
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class MovieRemoteImpl @Inject constructor(
    private val movieService: MovieService
) : MovieRemote {


    override suspend fun fetchImages(): List<Movie> {
        return movieService.getCurrentMovie(
            title = "Avengers: Endgame",
            year = "2019",
            plot = "full",
            key = "3e6675f5"
        ).movies
            .map {
                Movie(it.released, it.poster, it.plot, it.title, it.imdbID, it.year)
            }
    }
}