package com.example.cache.tmdbmovies

import com.example.cache.tmdbmovies.dao.TMDbMovieDao
import com.example.cache.tmdbmovies.model.mapToDomainModel
import com.example.cache.tmdbmovies.model.mapToRoomModel
import com.example.data.tmdbmovie.TMDbMovieCache
import com.example.domain.tmdbmovie.model.Result
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TMDbMovieCacheImpl @Inject constructor(
    private val tmDbMovieDao: TMDbMovieDao
) : TMDbMovieCache {
    override suspend fun getTMDbMovieById(id: String): Result {
        return tmDbMovieDao.getTMDbMovieByID(id).mapToDomainModel()
    }

    override suspend fun requestTMDbMovies(tmdbTitleToSearchFor: String?): List<Result> {
        return tmDbMovieDao.getTMDbMoviesForTitle("%$tmdbTitleToSearchFor%").map { databaseTMDbMovie ->
            databaseTMDbMovie.mapToDomainModel()
        }
    }

    override suspend fun storeTMDbMovies(tmdbMovies: List<Result>) {
        tmDbMovieDao.insertAllSuspend(tmdbMovies.map { domainTMDbMovie ->
            domainTMDbMovie.mapToRoomModel()
        })
    }
}