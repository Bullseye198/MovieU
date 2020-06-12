package com.example.remote.injection

import com.example.data.movie.MovieRemote
import com.example.data.tmdbmovie.TMDbMovieRemote
import com.example.data.tmdbmovie.TMDbMovieRepoImpl
import com.example.remote.movie.MovieRemoteImpl
import dagger.Binds
import dagger.Module

@Module
interface RemoteModuleBinds {

    @Binds
    fun bindMovieRemote(movieRemoteImpl: MovieRemoteImpl): MovieRemote

    @Binds
    fun bindTMDbMovieRemote(tmDbMovieRepoImpl: TMDbMovieRepoImpl): TMDbMovieRemote
}