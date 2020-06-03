package com.example.movieu.core.injection.module

import com.example.cache.movies.RoomMovieDatabase
import dagger.Module
import dagger.Provides

@Module
object DaoModule {

    @Provides
    fun provideMovieDao(roomMovieDatabase: RoomMovieDatabase) = roomMovieDatabase.roomMovieDao()

    @Provides
    fun provideRatingsDao(roomMovieDatabase: RoomMovieDatabase) = roomMovieDatabase.roomRatingsDao()

}