package com.example.cache.tmdbmovies

import com.example.cache.tmdbmovies.dao.*
import com.example.cache.tmdbmovies.dao.tvdetaildao.*
import com.example.cache.tmdbmovies.model.*
import com.example.data.tmdbmovie.TMDbMovieCache
import com.example.domain.movie.model.OMDbBaseInformation
import com.example.domain.tmdbmovie.model.*
import com.example.domain.tmdbmovie.model.tvdetail.TMDbTvDetail
import com.example.domain.tmdbmovie.model.tvlist.TvListResult
import io.reactivex.Flowable
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TMDbMovieCacheImpl @Inject constructor(
    private val tmDbMovieDao: TMDbMovieDao,
    private val genreDao: TMDbGenreDao,
    private val spokenLanguageDao: TMDbSpokenLanguageDao,
    private val castDao: TMDbCastDao,
    private val crewDao: TMDbCrewDao,
    private val tvDetailCreatedByDao: TvDetailCreatedByDao,
    private val tvDetailGenreDao: TvDetailGenreDao,
    private val tvDetailLastEpisodeToAirDao: TvDetailLastEpisodeToAirDao,
    private val tvDetailNetworkDao: TvDetailNetworkDao,
    private val tvDetailProductionCompanyDao: TvDetailProductionCompanyDao,
    private val tvDetailSeasonDao: TvDetailSeasonDao
) : TMDbMovieCache {

    override fun observeTMDbMovies(): Flowable<List<Result>> {
        return tmDbMovieDao.observeTMDbMovies()
            .map { roomTMDbMovies -> roomTMDbMovies.map { it.mapToDomainModelList() } }
    }

    override fun observeTMDbMovieDetail(id: Int): Flowable<TMDbMovieDetail> {
        return tmDbMovieDao.observeTMDbMovieDetail(id)
            .map { roomTMDbMovieDetail -> roomTMDbMovieDetail.mapToDomainModel() }
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

        castDao.InsertCast(credits.cast.map { cast: Cast -> cast.mapToRoomCast(credits.id.toString()) })
        crewDao.InsertCrew(credits.crew.map { crew: Crew ->
            crew.mapToRoomCrew(credits.id.toString())
        })


    }

    override suspend fun storeTMDbTvList(tmdbTvList: List<TvListResult>) {
        TODO("Not yet implemented")
    }

    override suspend fun storeTMDbTvDetail(tmdbTvDetail: TMDbTvDetail) {
        TODO("Not yet implemented")
    }

    override suspend fun addOmdbInformation(omdbOMDbBaseInformation: OMDbBaseInformation) {
        return tmDbMovieDao.updateTMDbMovie(
            omdbOMDbBaseInformation.imdbID,
            omdbOMDbBaseInformation.imdbRating
        )
    }
}