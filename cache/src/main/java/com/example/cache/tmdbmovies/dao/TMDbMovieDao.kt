package com.example.cache.tmdbmovies.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.cache.tmdbmovies.model.TMDbCachedRoomResultFull

@Dao
interface TMDbMovieDao {

    @Query("SELECT * FROM tmdbMovie")
    suspend fun getTMDbMovies(): List<TMDbCachedRoomResultFull>

    @Query("SELECT * FROM tmdbMovie WHERE id =:id")
    suspend fun getTMDbMovieByID(id: String): TMDbCachedRoomResultFull

    @Query("SELECT * FROM tmdbMovie WHERE title LIKE :titleToSearchFor")
    suspend fun getTMDbMoviesForTitle(titleToSearchFor: String): List<TMDbCachedRoomResultFull>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllSuspend(entities: List<TMDbCachedRoomResultFull>)
}