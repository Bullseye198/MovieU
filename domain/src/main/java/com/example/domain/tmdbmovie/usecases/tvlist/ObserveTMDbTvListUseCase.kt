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
) : FlowableUseCase<List<TvListResult>, Void?>(rxSchedulers) {

    private val localDatabaseSearchStream: BehaviorSubject<String> = BehaviorSubject.create()

    override fun buildUseCaseObservable(params: Void?): Flowable<List<TvListResult>> {
        return tmDbMovieRepository.observeTMDbTvList()
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

    fun onSearchTermChanged(newSearchTerm: String) {
        localDatabaseSearchStream.onNext(newSearchTerm)
    }
}