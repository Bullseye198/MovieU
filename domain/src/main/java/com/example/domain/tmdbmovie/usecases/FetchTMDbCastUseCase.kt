package com.example.domain.tmdbmovie.usecases

import com.example.domain.tmdbmovie.TMDbMovieRepository
import com.example.domain.tmdbmovie.model.Cast
import java.lang.Exception
import javax.inject.Inject

class FetchTMDbCastUseCase @Inject constructor(
    private val tmDbMovieRepository: TMDbMovieRepository
) {

   suspend fun fetchCast(id: Int): List<Cast> {
        try {
            return tmDbMovieRepository.fetchTMDbCast(id)
        } catch (e: Exception) {
            val x = e
        }
        return tmDbMovieRepository.fetchTMDbCast(id)

    }
}