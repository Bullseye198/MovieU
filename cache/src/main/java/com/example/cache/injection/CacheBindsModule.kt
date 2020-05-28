package com.example.cache.injection

import com.example.cache.movies.MovieCacheImpl
import com.example.data.movie.MovieCache
import dagger.Binds
import dagger.Module

@Module
interface CacheBindsModule {

    @Binds
    fun bindMoviesCached(movieCacheImpl: MovieCacheImpl): MovieCache
}