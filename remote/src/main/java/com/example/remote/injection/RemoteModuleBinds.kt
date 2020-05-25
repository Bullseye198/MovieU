package com.example.remote.injection

import com.example.data.movie.MovieRemote
import com.example.data.movie.MovieRepoImpl
import dagger.Binds
import dagger.Module

@Module
interface RemoteModuleBinds {

    @Binds
    fun bindMovieRemote(movieRepoImpl: MovieRepoImpl): MovieRemote
}