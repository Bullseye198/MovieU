package com.example.remote.movie

import com.example.remote.injection.RemoteModuleBinds
import dagger.Module
import dagger.Provides
import okhttp3.Interceptor
import javax.inject.Singleton

@Module(includes = [RemoteModuleBinds::class])
object RemoteModule {

    @Provides
    @Singleton
    fun provideMovieService(
        chuckerInterceptor: Interceptor
    ): MovieService {
        return MovieServiceFactory.makeMovieService(
            chuckerInterceptor
        )
    }
}