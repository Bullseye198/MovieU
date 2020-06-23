package com.example.domain.tmdbmovie.usecases.tvlist

import com.cm.base.executor.AppRxSchedulers
import com.cm.base.interactors.base.FlowableUseCase
import com.example.domain.tmdbmovie.TMDbMovieRepository
import com.example.domain.tmdbmovie.model.tvlist.TvListResult
import io.reactivex.BackpressureStrategy
import io.reactivex.Flowable
import io.reactivex.rxkotlin.combineLatest
import io.reactivex.subjects.BehaviorSubject
import java.util.*
import javax.inject.Inject

class ObserveTMDbTvListUseCase @Inject constructor(
    private val tmDbMovieRepository: TMDbMovieRepository,
    rxSchedulers: AppRxSchedulers
) : FlowableUseCase<List<TvListResult>, ObserveTMDbTvListUseCase.Params>(rxSchedulers) {

    private val localDatabaseSearchStream: BehaviorSubject<String> = BehaviorSubject.create()

    override fun buildUseCaseObservable(params: Params?): Flowable<List<TvListResult>> {
        return tmDbMovieRepository.observeTMDbTvListForTitle(params!!.nameToSearchFor)
            .combineLatest(localDatabaseSearchStream.toFlowable(BackpressureStrategy.LATEST))
            .map { tmdbTvWithSearchTerm ->
                val tv = tmdbTvWithSearchTerm.first
                val searchTerm = tmdbTvWithSearchTerm.second

                val tmdbTvForSearchTerm = tv.filter {
                    it.name.toLowerCase(Locale.ROOT).contains(searchTerm.toLowerCase(Locale.ROOT))
                }
                tmdbTvForSearchTerm.sortedWith(compareBy({ it.id }, { it.name }))
            }
    }

    data class Params(
        val titleToSearchFor: String,
        val nameToSearchFor: String
    )
}