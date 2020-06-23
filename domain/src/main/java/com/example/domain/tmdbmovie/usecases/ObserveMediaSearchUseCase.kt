package com.example.domain.tmdbmovie.usecases

import com.cm.base.executor.AppRxSchedulers
import com.cm.base.interactors.base.FlowableUseCase
import com.example.domain.tmdbmovie.TMDbMovieRepository
import com.example.domain.tmdbmovie.model.MediaList
import io.reactivex.BackpressureStrategy
import io.reactivex.Flowable
import io.reactivex.rxkotlin.combineLatest
import io.reactivex.subjects.BehaviorSubject
import java.util.*
import javax.inject.Inject

class ObserveMediaSearchUseCase @Inject constructor(
    private val tmDbMovieRepository: TMDbMovieRepository,
    rxSchedulers: AppRxSchedulers
) : FlowableUseCase<List<MediaList>, Void?>(rxSchedulers) {

    private val localDatabaseSearchStream: BehaviorSubject<String> = BehaviorSubject.create()

    override fun buildUseCaseObservable(params: Void?): Flowable<List<MediaList>> {
        return tmDbMovieRepository.observeTMDbTvList()
            .combineLatest(tmDbMovieRepository.observeTMDbMovies(), localDatabaseSearchStream.toFlowable(BackpressureStrategy.LATEST))
            .map {
                val series = it.first
                val  movies = it.second
                val searchTerm = it.third
                val mappedMovies = movies
                    .filter { movie ->
                        movie.title.toLowerCase(Locale.ROOT).contains(
                            searchTerm.toLowerCase(
                                Locale.ROOT
                            )
                        )
                    }
                    .sortedWith(compareBy({ it.id }, { it.title }))
                    .map { movie ->
                        MediaList(
                            movie.posterPath,
                            movie.id,
                            movie.title,
                            movie.releaseDate,
                            false
                        )
                    }
                val mappedSeries = series.filter { tvSeries ->
                    tvSeries.name.toLowerCase(Locale.ROOT).contains(
                        searchTerm.toLowerCase(
                            Locale.ROOT
                        )
                    )
                }
                    .sortedWith(compareBy({ it.id }, { it.name }))
                    .map {
                        MediaList(
                            it.posterPath,
                            it.id,
                            it.name,
                            it.firstAirDate,
                            true
                        )
                    }
                mappedMovies + mappedSeries
            }

    }

    fun onSearchTermChanged(newSearchTerm: String) {
        localDatabaseSearchStream.onNext(newSearchTerm)
    }
}