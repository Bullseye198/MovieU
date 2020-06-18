package com.example.cache.tmdbmovies

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.cache.tmdbmovies.dao.*
import com.example.cache.tmdbmovies.model.*

private const val DATABASE = "tmdbMovie"

@Database(
    entities = [TMDbCachedRoomResultFull::class, RoomGenre::class, RoomSpokenLanguage::class, RoomCast::class, RoomCrew::class],
    version = 5,
    exportSchema = false
)

abstract class TMDbRoomMovieDatabase : RoomDatabase() {
    abstract fun tmdbRoomMovieDao(): TMDbMovieDao
    abstract fun tmdbRoomGenreDao(): TMDbGenreDao
    abstract fun tmdbRoomSpokenLanguageDao(): TMDbSpokenLanguageDao
    abstract fun tmdbRoomCastDao(): TMDbCastDao
    abstract fun tmdbRoomCrewDao(): TMDbCrewDao
}