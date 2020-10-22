package com.example.cache.tmdbmovies

import com.example.cache.tmdbmovies.dao.tmdbmoviesdao.*
import com.example.cache.tmdbmovies.dao.tmdbtvseriesdao.*
import com.example.cache.tmdbmovies.model.roommoviedetail.mapToRoomCast
import com.example.cache.tmdbmovies.model.roommoviedetail.mapToRoomCrew
import com.example.cache.tmdbmovies.model.roommoviedetail.mapToRoomGenre
import com.example.cache.tmdbmovies.model.roommoviedetail.mapToRoomSpokenLanguage
import com.example.cache.tmdbmovies.model.roommovielist.mapToDomainModel
import com.example.cache.tmdbmovies.model.roommovielist.mapToDomainModelList
import com.example.cache.tmdbmovies.model.roommovielist.mapToFullRoomModel
import com.example.cache.tmdbmovies.model.roommovielist.mapToRoomModel
import com.example.cache.tmdbmovies.model.roomtvdetail.*
import com.example.cache.tmdbmovies.model.roomtvlist.mapToDomainTvDetailResult
import com.example.cache.tmdbmovies.model.roomtvlist.mapToDomainTvListResult
import com.example.cache.tmdbmovies.model.roomtvlist.mapToRoomTvListResult
import com.example.data.tmdbmovie.TMDbMovieCache
import com.example.domain.movie.model.OMDbBaseInformation
import com.example.domain.tmdbmovie.model.moviedetail.Cast
import com.example.domain.tmdbmovie.model.moviedetail.Credits
import com.example.domain.tmdbmovie.model.moviedetail.Crew
import com.example.domain.tmdbmovie.model.moviedetail.TMDbMovieDetail
import com.example.domain.tmdbmovie.model.tvdetail.TMDbTvDetail
import com.example.domain.tmdbmovie.model.movielist.Result
import com.example.domain.tmdbmovie.model.tvlist.TvListResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TMDbMovieCacheImpl @Inject constructor(
    private val tmDbMovieDao: TMDbMovieDao,
    private val genreDao: TMDbGenreDao,
    private val spokenLanguageDao: TMDbSpokenLanguageDao,
    private val castDao: TMDbCastDao,
    private val crewDao: TMDbCrewDao,
    private val tmdbTvSeriesDao: TMDbTvSeriesDao,
    private val tvDetailCreatedByDao: TvDetailCreatedByDao,
    private val tvDetailGenreDao: TvDetailGenreDao,
    private val tvDetailLastEpisodeToAirDao: TvDetailLastEpisodeToAirDao,
    private val tvDetailNetworkDao: TvDetailNetworkDao,
    private val tvDetailProductionCompanyDao: TvDetailProductionCompanyDao,
    private val tvDetailSeasonDao: TvDetailSeasonDao,
    private val tvDetailLanguagesDao: TvDetailLanguagesDao
) : TMDbMovieCache {

    override fun observeTMDbMoviesForTitle(titleToSearchFor: String): Flow<List<Result>> {
        return tmDbMovieDao.observeTMDbMoviesForTitle(titleToSearchFor)
            .map { roomTMDbMovies -> roomTMDbMovies.map { it.mapToDomainModelList() } }
    }

    override fun observeTMDbMovieDetail(id: Int): Flow<TMDbMovieDetail> {
        return tmDbMovieDao.observeTMDbMovieDetail(id)
            .map { roomTMDbMovieDetail -> roomTMDbMovieDetail.mapToDomainModel() }
    }

    override fun observeTMDbTvListForTitle(nameToSearchFor: String): Flow<List<TvListResult>> {
        return tmdbTvSeriesDao.observeTMDbTvListForName(nameToSearchFor)
            .map { roomTvList -> roomTvList.map { it.mapToDomainTvListResult() } }
    }

    override fun observeTMDbTvDetail(id: Int): Flow<TMDbTvDetail> {
        return tmdbTvSeriesDao.observeTMDbTvDetail(id)
            .map { roomTvDetail -> roomTvDetail.mapToDomainTvDetailResult() }
    }


    override suspend fun storeTMDbMovies(tmdbMovies: List<Result>) {
        tmDbMovieDao.insertAllIgnore(tmdbMovies.map { domainTMDbMovie ->
            domainTMDbMovie.mapToRoomModel()
        })
    }

    override suspend fun storeTMDbMovieDetail(tmDbMovieDetail: TMDbMovieDetail) {
        tmDbMovieDao.insertOneSuspend(tmDbMovieDetail.mapToFullRoomModel())
        genreDao.InsertGenre(tmDbMovieDetail.genres.map { tmdbMovieRatings ->
            tmdbMovieRatings.mapToRoomGenre(tmDbMovieDetail.id)
        })
        spokenLanguageDao.InsertSpokenLanguage(tmDbMovieDetail.spokenLanguages.map { tmdbMovieSpokenLanguages ->
            tmdbMovieSpokenLanguages.mapToRoomSpokenLanguage(tmDbMovieDetail.id)
        })
    }

    override suspend fun storeTMDbCredits(credits: Credits) {

        castDao.InsertCast(credits.cast.map { cast: Cast -> cast.mapToRoomCast(credits.id) })
        crewDao.InsertCrew(credits.crew.map { crew: Crew ->
            crew.mapToRoomCrew(credits.id)
        })
    }

    override suspend fun storeTMDbTvList(tmdbTvList: List<TvListResult>) {
        tmdbTvSeriesDao.insertAllIgnore(tmdbTvList.map { domainTvList ->
            domainTvList.mapToRoomTvListResult()
        })
    }

    override suspend fun storeTMDbTvDetail(tmdbTvDetail: TMDbTvDetail) {
        tmdbTvSeriesDao.insertOneSuspend(tmdbTvDetail.mapToRoomTvListResult())

        tvDetailCreatedByDao.insertTvDetailCreatedBy(tmdbTvDetail.tvDetailCreatedBy.map { tmdbTvDetailCreatedBy ->
            tmdbTvDetailCreatedBy.mapToRoomTvDetailCreatedBy(tmdbTvDetail.id.toString())
        })

        tvDetailGenreDao.insertTvDetailGenre(tmdbTvDetail.tvDetailGenres.map { tmdbTvDetailGenre ->
            tmdbTvDetailGenre.mapToRoomTvDetailGenre(tmdbTvDetail.id.toString())
        })

        tvDetailLastEpisodeToAirDao.insertTvDetailLastEpisodeToAir(
            tmdbTvDetail.tvDetailLastEpisodeToAir?.mapToRoomTvDetailLastEpisodeToAir(
                tmdbTvDetail.id.toString()
            )
        )

        tvDetailNetworkDao.insertTvDetailNetwork(tmdbTvDetail.tvDetailNetworks.map { tmdbTvDetailNetwork ->
            tmdbTvDetailNetwork.mapToRoomTvDetailNetwork(
                tmdbTvDetail.id.toString()
            )
        })

        tvDetailProductionCompanyDao.insertTvDetailProductionCompany(tmdbTvDetail.tvDetailProductionCompanies.map { tmdbTvDetailProductionCompanies ->
            tmdbTvDetailProductionCompanies.mapToRoomTvDetailProductionCompany(
                tmdbTvDetail.id.toString()
            )
        })

        tvDetailSeasonDao.insertTvDetailSeason(tmdbTvDetail.tvDetailSeasons.map { tmdbTvDetailSeason ->
            tmdbTvDetailSeason.mapToRoomTvDetailSeason(tmdbTvDetail.id.toString())
        })

        tvDetailLanguagesDao.insertTvDetailLanguages(tmdbTvDetail.languages?.map { tmdbTvDetailLanguages ->
            tmdbTvDetailLanguages.mapToRoomTvDetailLanguages(tmdbTvDetail.id.toString())
        })
    }

    override suspend fun addOmdbInformation(omdbOMDbBaseInformation: OMDbBaseInformation) {
        return tmDbMovieDao.updateTMDbMovie(
            omdbOMDbBaseInformation.imdbID,
            omdbOMDbBaseInformation.imdbRating
        )
    }
}