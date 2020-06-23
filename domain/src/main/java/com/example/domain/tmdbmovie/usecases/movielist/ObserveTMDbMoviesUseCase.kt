package com.example.domain.tmdbmovie.usecases.movielist

import com.cm.base.executor.AppRxSchedulers
import com.cm.base.interactors.base.FlowableUseCase
import com.example.domain.tmdbmovie.TMDbMovieRepository
import com.example.domain.tmdbmovie.model.movielist.Result
import io.reactivex.BackpressureStrategy
import io.reactivex.Flowable
import io.reactivex.rxkotlin.combineLatest
import io.reactivex.subjects.BehaviorSubject
import java.util.*
import javax.inject.Inject

class ObserveTMDbMoviesUseCase @Inject constructor(
    private val tmDbMovieRepository: TMDbMovieRepository,
    rxSchedulers: AppRxSchedulers
) : FlowableUseCase<List<Result>, ObserveTMDbMoviesUseCase.Params>(rxSchedulers) {

    private val localDatabaseSearchStream: BehaviorSubject<String> = BehaviorSubject.create()


    override fun buildUseCaseObservable(params: Params?): Flowable<List<Result>> {
        return tmDbMovieRepository.observeTMDbMoviesForTitle(params!!.titleToSearchFor)
            .combineLatest(localDatabaseSearchStream.toFlowable(BackpressureStrategy.LATEST))
            .map { tmdbMoviesWithSearchTerm ->
                val movies = tmdbMoviesWithSearchTerm.first
                val searchTerm = tmdbMoviesWithSearchTerm.second

                val tmdbMoviesForSearchTerm = movies.filter {
                    it.title.toLowerCase(Locale.ROOT).contains(searchTerm.toLowerCase(Locale.ROOT))
                }
                tmdbMoviesForSearchTerm.sortedWith(compareBy({ it.id }, { it.title }))
            }
    }

    data class Params(
        val titleToSearchFor: String,
        val nameToSearchFor: String
    )
}