package com.example.remote.injection

import com.example.data.movie.MovieRemote
import com.example.data.tmdbmovie.TMDbMovieRemote
import com.example.remote.movie.MovieRemoteImpl
import com.example.remote.tmdbmovie.TMDbMovieRemoteImpl
import dagger.Binds
import dagger.Module

@Module
interface RemoteModuleBinds {

    @Binds
    fun bindMovieRemote(movieRemoteImpl: MovieRemoteImpl): MovieRemote

    @Binds
    fun bindTMDbMovieRemote(tmDbMovieRemoteImpl: TMDbMovieRemoteImpl): TMDbMovieRemote
}