package com.example.remote.injection

import com.example.remote.movie.MovieService
import com.example.remote.movie.MovieServiceFactory
import com.example.remote.tmdbmovie.TMDbMovieService
import com.example.remote.tmdbmovie.TMDbMovieServiceFactory
import dagger.Module
import dagger.Provides
import okhttp3.Interceptor
import javax.inject.Singleton

/**
 * Module that provides all dependencies from the repository package/layer.
 */
@Module(includes = [RemoteModuleBinds::class])
object RemoteModule {

    @Provides
    @Singleton
    fun provideMovieService(
        chuckerInterceptor: Interceptor
    ): MovieService {
        return MovieServiceFactory.makeOMDbMovieService(
            chuckerInterceptor
        )
        //we need to return PhotoServiceFactory
    }

    @Provides
    fun provideTMDbMovieService(
        chuckerInterceptor: Interceptor
    ): TMDbMovieService {
        return TMDbMovieServiceFactory.makeTMDbMovieService(
            chuckerInterceptor
        )
    }
}