package com.example.data.injection

import com.example.data.movie.MovieRepoImpl
import com.example.domain.movie.IMovieRepository
import dagger.Binds
import dagger.Module

@Module
interface DataModule {

    @Binds
    fun bindIMovieRepository(movieRepoImpl: MovieRepoImpl): IMovieRepository
}