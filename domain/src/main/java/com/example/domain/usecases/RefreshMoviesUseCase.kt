package com.example.domain.usecases

import com.example.domain.movie.IMovieRepository
import javax.inject.Inject

class RefreshMoviesUseCase  @Inject constructor(
    private val iMovieRepository: IMovieRepository
){
    suspend fun refresh() {
        val serverMovies = iMovieRepository.fetchMovies()
        iMovieRepository.storeMovies(serverMovies)
    }
}