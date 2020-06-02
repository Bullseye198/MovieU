package com.example.cache.movies

import com.example.cache.movies.dao.MovieDao
import com.example.data.movie.MovieCache
import com.example.domain.movie.model.Movie
import com.example.domain.movie.model.MovieDetail
import io.reactivex.Flowable
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MovieCacheImpl @Inject constructor(
    private val movieDao: MovieDao
) : MovieCache {
    override suspend fun getMovieById(imdbID: String): Movie {
        return movieDao.getMovieByImdbID(imdbID).mapToDomainModel()
    }

    override suspend fun requestMovies(titleToSearchFor: String?): List<Movie> {
        return movieDao.getMoviesForTitle("%$titleToSearchFor%")
            .map { databaseMovie ->
                databaseMovie.mapToDomainModel()
            }
    }

    override fun observeMovies(): Flowable<List<Movie>> {
        return movieDao.observeMovies()
            .map { roomMovies ->
            roomMovies.map { it.mapToDomainModel() }
        }
    }

    override suspend fun storeMovies(movies: List<Movie>) {
        movieDao.insertAllSuspend(movies.map { domainMovie ->
            domainMovie.mapToRoomModel()
        })
    }

    override suspend fun storeMovieDetail(movieDetail: MovieDetail) {
        TODO("Not yet implemented")
    }


}