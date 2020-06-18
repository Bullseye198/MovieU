package com.example.cache.tmdbmovies

import com.example.cache.tmdbmovies.dao.TMDbGenreDao
import com.example.cache.tmdbmovies.dao.TMDbMovieDao
import com.example.cache.tmdbmovies.dao.TMDbSpokenLanguageDao
import com.example.cache.tmdbmovies.model.*
import com.example.data.tmdbmovie.TMDbMovieCache
import com.example.domain.movie.model.OMDbBaseInformation
import com.example.domain.tmdbmovie.model.Credits
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

    override fun observeTMDbMovies(): Flowable<List<Result>> {
        return tmDbMovieDao.observeTMDbMovies()
            .map { roomTMDbMovies -> roomTMDbMovies.map { it.mapToDomainModelList() } }
    }

    override fun observeTMDbMovieDetail(id: Int): Flowable<TMDbMovieDetail> {
        return tmDbMovieDao.observeTMDbMovieDetail(id)
            .map { roomTMDbMovieDetail -> roomTMDbMovieDetail.mapToDomainModel() }
    }

    override fun observeTMDbCredits(id: Int): Flowable<Credits> {
        TODO("Not yet implemented")
    }

    override suspend fun storeTMDbMovies(tmdbMovies: List<Result>) {
        tmDbMovieDao.insertAllIgnore(tmdbMovies.map { domainTMDbMovie ->
            domainTMDbMovie.mapToRoomModel()
        })
    }

    override suspend fun storeTMDbMovieDetail(tmDbMovieDetail: TMDbMovieDetail) {
        tmDbMovieDao.insertOneSuspend(tmDbMovieDetail.mapToFullRoomModel())
        genreDao.InsertGenre(tmDbMovieDetail.genres?.map { tmdbMovieRatings ->
            RoomGenre(
                tmdbMovieRatings.id,
                tmDbMovieDetail.id.toString(),
                tmdbMovieRatings.name
            )
        })
        spokenLanguageDao.InsertSpokenLanguage(tmDbMovieDetail.spokenLanguages?.map { tmdbMovieSpokenLanguages ->
            RoomSpokenLanguage(
                tmdbMovieSpokenLanguages.iso6391,
                tmDbMovieDetail.id,
                tmdbMovieSpokenLanguages.name
            )
        })
    }

    override suspend fun storeTMDbCredits(credits: Credits) {
        TODO("Not yet implemented")
    }

    override suspend fun addOmdbInformation(omdbOMDbBaseInformation: OMDbBaseInformation) {
        return tmDbMovieDao.updateTMDbMovie(
            omdbOMDbBaseInformation.imdbID,
            omdbOMDbBaseInformation.imdbRating
        )
    }
}