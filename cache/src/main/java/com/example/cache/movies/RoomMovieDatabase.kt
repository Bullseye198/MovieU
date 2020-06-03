package com.example.cache.movies

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.cache.movies.dao.MovieDao
import com.example.cache.movies.dao.RatingsDao
import com.example.cache.movies.model.RoomMovie
import com.example.cache.movies.model.RoomRatings

private const val DATABASE = "movie"

@Database(
    entities = [RoomMovie::class, RoomRatings::class],
    version = 4,
    exportSchema = false
)

abstract class RoomMovieDatabase : RoomDatabase() {
    abstract fun roomMovieDao(): MovieDao

    abstract fun roomRatingsDao(): RatingsDao
}

