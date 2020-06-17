package com.example.cache.tmdbmovies

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.cache.tmdbmovies.dao.TMDbGenreDao
import com.example.cache.tmdbmovies.dao.TMDbMovieDao
import com.example.cache.tmdbmovies.dao.TMDbSpokenLanguageDao
import com.example.cache.tmdbmovies.model.RoomGenre
import com.example.cache.tmdbmovies.model.RoomSpokenLanguage
import com.example.cache.tmdbmovies.model.TMDbCachedRoomResultFull

private const val DATABASE = "tmdbMovie"

@Database(
    entities = [TMDbCachedRoomResultFull::class, RoomGenre::class, RoomSpokenLanguage::class],
    version = 4,
    exportSchema = false
)

abstract class TMDbRoomMovieDatabase : RoomDatabase() {
    abstract fun tmdbRoomMovieDao(): TMDbMovieDao
    abstract fun tmdbRoomGenreDao(): TMDbGenreDao
    abstract fun tmdbRoomSpokenLanguageDao(): TMDbSpokenLanguageDao
}