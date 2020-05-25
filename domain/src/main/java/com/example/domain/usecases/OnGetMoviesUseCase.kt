package com.example.domain.usecases

import com.cm.base.executor.AppCoroutineDispatchers
import com.example.domain.movie.IMovieRepository
import javax.inject.Inject

class OnGetMoviesUseCase @Inject constructor(
    private val iMovieRepository: IMovieRepository,
    val appDispatchers: AppCoroutineDispatchers
){

    suspend fun getMovies() {

    }
}