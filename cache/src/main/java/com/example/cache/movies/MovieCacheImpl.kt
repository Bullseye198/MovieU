package com.example.cache.movies

import com.example.cache.movies.dao.MovieDao
import com.example.data.movie.MovieCache
import com.example.domain.movie.IMovieRepository
import com.example.domain.movie.model.Movie
import io.reactivex.Flowable
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MovieCacheImpl @Inject constructor(
    private val movieDao: MovieDao
) : MovieCache {

    override suspend fun requestMovies(): List<Movie> {
        return movieDao.getMovies()
            .map { databaseMovie ->
                databaseMovie.mapToDomainModel()
            }
    }

    override suspend fun observeMovies(): Flowable<List<Movie>> {
        return Flowable.empty()
    }

    override suspend fun storeMovies(movies: List<Movie>) {
        movieDao.insertAllSuspend(movies.map { domainMovie ->
            domainMovie.mapToRoomModel()
        })
    }


}