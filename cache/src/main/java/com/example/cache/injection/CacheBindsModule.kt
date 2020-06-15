package com.example.cache.injection

import com.example.cache.movies.MovieCacheImpl
import com.example.cache.tmdbmovies.TMDbMovieCacheImpl
import com.example.data.movie.MovieCache
import com.example.data.tmdbmovie.TMDbMovieCache
import dagger.Binds
import dagger.Module

@Module
interface CacheBindsModule {

    @Binds
    fun bindMoviesCached(movieCacheImpl: MovieCacheImpl): MovieCache

    @Binds
    fun bindTMDbMoviesCached(tmDbMovieCacheImpl: TMDbMovieCacheImpl): TMDbMovieCache
}