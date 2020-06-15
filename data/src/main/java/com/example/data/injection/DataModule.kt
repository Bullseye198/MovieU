package com.example.data.injection

import com.example.data.movie.MovieRepoImpl
import com.example.data.tmdbmovie.TMDbMovieRepoImpl
import com.example.domain.movie.IMovieRepository
import com.example.domain.tmdbmovie.TMDbMovieRepository
import dagger.Binds
import dagger.Module

@Module
interface DataModule {

    @Binds
    fun bindIMovieRepository(movieRepoImpl: MovieRepoImpl): IMovieRepository

    @Binds
    fun bindTMDbMovieRepository(tmDbMovieRepoImpl: TMDbMovieRepoImpl): TMDbMovieRepository
}