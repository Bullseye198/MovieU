package com.example.domain.usecases

import com.cm.base.executor.AppRxSchedulers
import com.cm.base.interactors.base.FlowableUseCase
import com.example.domain.movie.IMovieRepository
import com.example.domain.movie.model.Movie
import io.reactivex.BackpressureStrategy
import io.reactivex.Flowable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.combineLatest
import io.reactivex.subjects.BehaviorSubject
import io.reactivex.subscribers.DisposableSubscriber
import javax.inject.Inject

class ObserveMoviesUseCase @Inject constructor(
    private val iMovieRepository: IMovieRepository,
    private val rxSchedulers: AppRxSchedulers
) : FlowableUseCase<List<Movie>, Void?>(rxSchedulers) {

    private val localDatabaseSearchStream: BehaviorSubject<String> = BehaviorSubject.create()

    override fun buildUseCaseObservable(params: Void?): Flowable<List<Movie>> {
        return iMovieRepository.observeMovies()
            .combineLatest(localDatabaseSearchStream.toFlowable(BackpressureStrategy.LATEST))
            .map { moviesWithSearchTerm ->
                val movies = moviesWithSearchTerm.first
                val searchTerm = moviesWithSearchTerm.second

                val moviesForSearchTerm = movies.filter {
                    it.title.toLowerCase().contains(searchTerm.toLowerCase())
                }
                moviesForSearchTerm.sortedWith(compareBy({ it.imdbID }, { it.title }))
            }
    }

    fun onSearchTermChanged(newSearchTerm: String) {
        localDatabaseSearchStream.onNext(newSearchTerm)
    }
}


