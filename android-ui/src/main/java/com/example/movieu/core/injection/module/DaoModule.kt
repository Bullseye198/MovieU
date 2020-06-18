package com.example.movieu.core.injection.module

import com.example.cache.tmdbmovies.TMDbRoomMovieDatabase
import dagger.Module
import dagger.Provides

@Module
object DaoModule {

    @Provides
    fun provideTMDbMovieDao(tmDbRoomMovieDatabase: TMDbRoomMovieDatabase) =
        tmDbRoomMovieDatabase.tmdbRoomMovieDao()

    @Provides
    fun provideTMDbGenreDao(tmDbRoomMovieDatabase: TMDbRoomMovieDatabase) =
        tmDbRoomMovieDatabase.tmdbRoomGenreDao()

    @Provides
    fun provideTMDbSpokenLanguageDao(tmDbRoomMovieDatabase: TMDbRoomMovieDatabase) =
        tmDbRoomMovieDatabase.tmdbRoomSpokenLanguageDao()

    @Provides
    fun provideTMDbCast(tmDbRoomMovieDatabase: TMDbRoomMovieDatabase) =
        tmDbRoomMovieDatabase.tmdbRoomCastDao()

    @Provides
    fun provideTMDbCrew(tmDbRoomMovieDatabase: TMDbRoomMovieDatabase) =
        tmDbRoomMovieDatabase.tmdbRoomCrewDao()
}