package com.example.data.tmdbmovie

import com.example.domain.movie.model.OMDbBaseInformation
import com.example.domain.tmdbmovie.TMDbMovieRepository
import com.example.domain.tmdbmovie.model.moviedetail.Credits
import com.example.domain.tmdbmovie.model.movielist.Result
import com.example.domain.tmdbmovie.model.moviedetail.TMDbMovieDetail
import com.example.domain.tmdbmovie.model.tvdetail.TMDbTvDetail
import com.example.domain.tmdbmovie.model.tvlist.TvListResult
import io.reactivex.Flowable
import javax.inject.Inject

class TMDbMovieRepoImpl @Inject constructor(
    private val tmDbMovieRemote: TMDbMovieRemote,
    private val tmDbMovieCache: TMDbMovieCache
) : TMDbMovieRepository {
    override fun observeTMDbMoviesForTitle(titleToSearchFor: String): Flowable<List<Result>> {
        return tmDbMovieCache.observeTMDbMoviesForTitle(titleToSearchFor)
    }


    override fun observeTMDbMovieDetail(id: Int): Flowable<TMDbMovieDetail> {
        return tmDbMovieCache.observeTMDbMovieDetail(id)
    }

    override fun observeTMDbTvListForTitle(nameToSearchFor: String): Flowable<List<TvListResult>> {
        return tmDbMovieCache.observeTMDbTvListForTitle(nameToSearchFor)
    }


    override fun observeTMDbTvDetail(id: Int): Flowable<TMDbTvDetail> {
        return tmDbMovieCache.observeTMDbTvDetail(id)
    }

    override suspend fun fetchTMDbMovies(tmdbTitleToSearchFor: String): List<Result> {
        return tmDbMovieRemote.fetchTMDbMovies(tmdbTitleToSearchFor)
    }

    override suspend fun storeTMDbMovies(tmdbMovies: List<Result>) {
        return tmDbMovieCache.storeTMDbMovies(tmdbMovies)
    }

    override suspend fun fetchTMDbMovieDetail(id: Int): TMDbMovieDetail {
        return tmDbMovieRemote.fetchTMDbMovieDetail(id)
    }

    override suspend fun fetchTMDbCredits(id: Int): Credits {
        return tmDbMovieRemote.fetchTMDbCredits(id)
    }

    override suspend fun fetchTMDbTvList(tmdbTvToSearchFor: String): List<TvListResult> {
        return tmDbMovieRemote.fetchTMDbTvList(tmdbTvToSearchFor)
    }

    override suspend fun fetchTMDbTvDetail(id: Int): TMDbTvDetail {
        return tmDbMovieRemote.fetchTMDbTvDetail(id)
    }

    override suspend fun storeTMDbMovieDetail(tmDbMovieDetail: TMDbMovieDetail) {
        return tmDbMovieCache.storeTMDbMovieDetail(tmDbMovieDetail)
    }

    override suspend fun storeTMDbCredits(credits: Credits) {
        return tmDbMovieCache.storeTMDbCredits(credits)
    }

    override suspend fun storeTMDbTvList(tmdbTvList: List<TvListResult>) {
        return tmDbMovieCache.storeTMDbTvList(tmdbTvList)
    }

    override suspend fun storeTMDbTvDetail(tmdbTvDetail: TMDbTvDetail) {
        return tmDbMovieCache.storeTMDbTvDetail(tmdbTvDetail)
    }

    override suspend fun addOmdbInformation(omdbOMDbBaseInformation: OMDbBaseInformation) {
        return tmDbMovieCache.addOmdbInformation(omdbOMDbBaseInformation)
    }
}