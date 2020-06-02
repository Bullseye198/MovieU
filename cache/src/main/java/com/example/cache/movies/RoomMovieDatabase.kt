package com.example.cache.movies

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.cache.movies.dao.MovieDao
import com.example.cache.movies.model.RoomMovie

private const val DATABASE = "movie"

@Database(
    entities = [RoomMovie::class],
    version = 3,
    exportSchema = false
)

abstract class RoomMovieDatabase : RoomDatabase() {
    abstract fun roomMovieDao(): MovieDao


}

