package com.example.data.tmdbmovie

import com.example.domain.movie.model.OMDbBaseInformation
import com.example.domain.tmdbmovie.TMDbMovieRepository
import com.example.domain.tmdbmovie.model.Result
import com.example.domain.tmdbmovie.model.TMDbMovieDetail
import io.reactivex.Flowable
import javax.inject.Inject

class TMDbMovieRepoImpl @Inject constructor(
    private val tmDbMovieRemote: TMDbMovieRemote,
    private val tmDbMovieCache: TMDbMovieCache
) : TMDbMovieRepository {

    override fun observeTMDbMovies(): Flowable<List<Result>> {
        return tmDbMovieCache.observeTMDbMovies()
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

    override suspend fun storeTMDbMovieDetail(tmDbMovieDetail: TMDbMovieDetail) {
        return tmDbMovieCache.storeTMDbMovieDetail(tmDbMovieDetail)
    }

    override fun observeTMDbMovieDetail(id: Int): Flowable<TMDbMovieDetail> {
        return tmDbMovieCache.observeTMDbMovieDetail(id)
    }

    override suspend fun addOmdbInformation(omdbOMDbBaseInformation: OMDbBaseInformation) {
        return tmDbMovieCache.addOmdbInformation(omdbOMDbBaseInformation)
    }


}