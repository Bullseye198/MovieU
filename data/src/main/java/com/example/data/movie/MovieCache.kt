package com.example.data.movie

import com.example.domain.movie.model.Movie
import io.reactivex.Flowable

interface MovieCache {

    suspend fun requestMovies(): List<Movie>

    suspend fun observeMovies(): Flowable<List<Movie>>

    suspend fun storeMovies(movies: List<Movie>)
}