package com.example.cache.movies

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.cache.movies.dao.MovieDao

private const val DATABASE = "movie"

@Database(
    entities = [RoomMovie::class],
    version = 2,
    exportSchema = false
)

abstract class RoomMovieDatabase : RoomDatabase() {
    abstract fun roomMovieDao(): MovieDao


}

