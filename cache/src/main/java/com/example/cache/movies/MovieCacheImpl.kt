package com.example.cache.movies

import com.example.cache.movies.dao.MovieDao
import com.example.cache.movies.dao.RatingsDao
import com.example.cache.movies.model.*
import com.example.data.movie.MovieCache
import com.example.domain.movie.model.Movie
import com.example.domain.movie.model.MovieDetail
import io.reactivex.Flowable
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MovieCacheImpl @Inject constructor(
    private val movieDao: MovieDao,
    private val ratingsDao: RatingsDao
) : MovieCache {
    override suspend fun getMovieById(imdbID: String): Movie {
        return movieDao.getMovieByImdbID(imdbID).mapToDomainModelList()
    }

    override suspend fun requestMovies(titleToSearchFor: String?): List<Movie> {
        return movieDao.getMoviesForTitle("%$titleToSearchFor%")
            .map { databaseMovie ->
                databaseMovie.mapToDomainModelList()
            }
    }

    override fun observeMovies(): Flowable<List<Movie>> {
        return movieDao.observeMovies()
            .map { roomMovies ->
                roomMovies.map { it.mapToDomainModelList() }
            }
    }

    override suspend fun storeMovies(movies: List<Movie>) {
        movieDao.insertAllIgnore(movies.map { domainMovie ->
            domainMovie.mapToRoomModel()
        })
    }

    override suspend fun storeMovieDetail(movieDetail: MovieDetail) {
        movieDao.insertOneSuspend(movieDetail.mapToFullRoomModel())
        ratingsDao.InsertRatings(movieDetail.ratings?.map { movieRatings ->
            RoomRatings(
                movieRatings.source,
                movieDetail.imdbID,
                movieRatings.value
            )
        })
    }

    override fun observeMovieDetail(imdbID: String): Flowable<MovieDetail> {
        return movieDao.observeMovieDetail(imdbID)
            .map { roomMovieDetail -> roomMovieDetail.mapToDomainModel() }
    }
}