package com.example.domain.tmdbmovie.usecases

import com.cm.base.executor.AppRxSchedulers
import com.cm.base.interactors.base.FlowableUseCase
import com.example.domain.tmdbmovie.TMDbMovieRepository
import com.example.domain.tmdbmovie.model.Result
import io.reactivex.BackpressureStrategy
import io.reactivex.Flowable
import io.reactivex.rxkotlin.combineLatest
import io.reactivex.subjects.BehaviorSubject
import java.util.*
import javax.inject.Inject

class ObserveTMDbMoviesUseCase @Inject constructor(
    private val tmDbMovieRepository: TMDbMovieRepository,
    rxSchedulers: AppRxSchedulers
) : FlowableUseCase<List<Result>, Void?>(rxSchedulers) {

    private val localDatabaseSearchStream: BehaviorSubject<String> = BehaviorSubject.create()


    override fun buildUseCaseObservable(params: Void?): Flowable<List<Result>> {
        return tmDbMovieRepository.observeTMDbMovies()
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

    fun onSearchTermChanged(newSearchTerm: String) {
        localDatabaseSearchStream.onNext(newSearchTerm)
    }


    suspend fun requestTMDbMovies(tmdbTitleToSearchFor: String): List<Result> {
        return tmDbMovieRepository.requestTMDbMovies(tmdbTitleToSearchFor)
    }
}