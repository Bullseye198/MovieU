package com.example.cache.tmdbmovies

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.cache.tmdbmovies.dao.TMDbMovieDao
import com.example.cache.tmdbmovies.model.TMDbCachedRoomMovie

private const val DATABASE = "tmdbMovie"

@Database(
    entities = [TMDbCachedRoomMovie::class],
    version = 3,
    exportSchema = false
)

abstract class TMDbRoomMovieDatabase : RoomDatabase() {
    abstract fun tmdbRoomMovieDao(): TMDbMovieDao
}