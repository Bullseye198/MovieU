package com.example.cache.tmdbmovies

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.cache.tmdbmovies.dao.TMDbMovieDao
import com.example.cache.tmdbmovies.model.TMDbCachedRoomResultFull

private const val DATABASE = "tmdbMovie"

@Database(
    entities = [TMDbCachedRoomResultFull::class],
    version = 3,
    exportSchema = false
)

abstract class TMDbRoomMovieDatabase : RoomDatabase() {
    abstract fun tmdbRoomMovieDao(): TMDbMovieDao
}