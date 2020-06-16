package com.example.movieu.core.injection.module

import com.example.cache.movies.RoomMovieDatabase
import com.example.cache.tmdbmovies.TMDbRoomMovieDatabase
import dagger.Module
import dagger.Provides

@Module
object DaoModule {

    @Provides
    fun provideMovieDao(roomMovieDatabase: RoomMovieDatabase) = roomMovieDatabase.roomMovieDao()

    @Provides
    fun provideRatingsDao(roomMovieDatabase: RoomMovieDatabase) = roomMovieDatabase.roomRatingsDao()

    @Provides
    fun provideTMDbMovieDao(tmDbRoomMovieDatabase: TMDbRoomMovieDatabase) =
        tmDbRoomMovieDatabase.tmdbRoomMovieDao()

    @Provides
    fun provideTMDbGenreDao(tmDbRoomMovieDatabase: TMDbRoomMovieDatabase) =
        tmDbRoomMovieDatabase.tmdbRoomGenreDao()

    @Provides
    fun provideTMDbSpokenLanguageDao(tmDbRoomMovieDatabase: TMDbRoomMovieDatabase) =
        tmDbRoomMovieDatabase.tmdbRoomSpokenLanguageDao()
}