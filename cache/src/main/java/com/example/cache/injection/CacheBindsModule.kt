package com.example.cache.injection

import com.example.cache.tmdbmovies.TMDbMovieCacheImpl
import com.example.data.tmdbmovie.TMDbMovieCache
import dagger.Binds
import dagger.Module

@Module
interface CacheBindsModule {

    @Binds
    fun bindTMDbMoviesCached(tmDbMovieCacheImpl: TMDbMovieCacheImpl): TMDbMovieCache
}