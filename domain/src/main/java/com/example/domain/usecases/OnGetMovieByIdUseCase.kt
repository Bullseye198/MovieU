package com.example.domain.usecases

import com.cm.base.executor.AppRxSchedulers
import com.example.domain.movie.IMovieRepository
import com.example.domain.movie.model.Movie
import io.reactivex.subscribers.DisposableSubscriber
import javax.inject.Inject

class OnGetMovieByIdUseCase @Inject constructor(
    private val iMovieRepository: IMovieRepository,
    val rxSchedulers: AppRxSchedulers
) {

     fun getMovie(josh: DisposableSubscriber<Movie>, imdbID: String) {
        iMovieRepository.observeMovies()
            .map { movies -> movies.first { it.imdbID == imdbID } }
            .subscribeOn(rxSchedulers.io)
            .observeOn(rxSchedulers.main)
            .subscribeWith(josh)
    }
}