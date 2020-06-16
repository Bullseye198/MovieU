package com.example.cache.tmdbmovies

import com.example.cache.tmdbmovies.dao.TMDbGenreDao
import com.example.cache.tmdbmovies.dao.TMDbMovieDao
import com.example.cache.tmdbmovies.dao.TMDbSpokenLanguageDao
import com.example.cache.tmdbmovies.model.mapToDomainModelList
import com.example.cache.tmdbmovies.model.mapToRoomModel
import com.example.data.tmdbmovie.TMDbMovieCache
import com.example.domain.tmdbmovie.model.Result
import com.example.domain.tmdbmovie.model.TMDbMovieDetail
import io.reactivex.Flowable
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TMDbMovieCacheImpl @Inject constructor(
    private val tmDbMovieDao: TMDbMovieDao,
    private val genreDao: TMDbGenreDao,
    private val spokenLanguageDao: TMDbSpokenLanguageDao
) : TMDbMovieCache {
    override suspend fun getTMDbMovieById(id: String): Result {
        return tmDbMovieDao.getTMDbMovieByID(id).mapToDomainModelList()
    }

    override suspend fun requestTMDbMovies(tmdbTitleToSearchFor: String?): List<Result> {
        return tmDbMovieDao.getTMDbMoviesForTitle("%$tmdbTitleToSearchFor%").map { databaseTMDbMovie ->
            databaseTMDbMovie.mapToDomainModelList()
        }
    }

    override suspend fun observeTMDbMovies(): Flowable<List<Result>> {
        TODO("Not yet implemented")
    }

    override suspend fun storeTMDbMovies(tmdbMovies: List<Result>) {
        tmDbMovieDao.insertAllSuspend(tmdbMovies.map { domainTMDbMovie ->
            domainTMDbMovie.mapToRoomModel()
        })
    }

    override suspend fun storeTMDbMovieDetail(tmDbMovieDetail: TMDbMovieDetail) {
        TODO("Not yet implemented")
    }

    override fun observeTMDbMovieDetail(id: String): Flowable<TMDbMovieDetail> {
        TODO("Not yet implemented")
    }
}