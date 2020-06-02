package com.example.domain.usecases

import com.example.domain.movie.IMovieRepository
import java.lang.Exception
import javax.inject.Inject

class RefreshMovieDetailUseCase @Inject constructor(
    private val iMovieRepository: IMovieRepository
) {
    suspend fun refresh(imdbID: String) {
        try {
            val serverMovieDetail = iMovieRepository.fetchMovieDetail(imdbID)
            iMovieRepository.storeMovieDetail(serverMovieDetail)
        } catch (e: Exception) {
        }
    }
}