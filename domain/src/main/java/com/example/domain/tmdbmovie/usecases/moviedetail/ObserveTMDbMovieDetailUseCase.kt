package com.example.domain.tmdbmovie.usecases.moviedetail

import com.cm.base.executor.AppCoroutineDispatchers
import com.cm.base.executor.AppRxSchedulers
import com.cm.base.interactors.base.FlowUseCase
import com.cm.base.interactors.base.FlowableUseCase
import com.example.domain.tmdbmovie.TMDbMovieRepository
import com.example.domain.tmdbmovie.model.moviedetail.TMDbMovieDetail
import io.reactivex.Flowable
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ObserveTMDbMovieDetailUseCase @Inject constructor(
    private val tmdbMovieRepository: TMDbMovieRepository,
    appCoroutineDispatchers: AppCoroutineDispatchers
) : FlowUseCase<TMDbMovieDetail, ObserveTMDbMovieDetailUseCase.Params>(appCoroutineDispatchers) {

    override fun buildStream(params: Params?): Flow<TMDbMovieDetail> {
        return tmdbMovieRepository.observeTMDbMovieDetail(params!!.id)
    }

    data class Params(
        val id: Int
    )
}