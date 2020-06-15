package com.example.cache.tmdbmovies.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.cache.tmdbmovies.model.TMDbCachedRoomMovie

@Dao
interface TMDbMovieDao {

    @Query("SELECT * FROM tmdbMovie")
    suspend fun getTMDbMovies(): List<TMDbCachedRoomMovie>

    @Query("SELECT * FROM tmdbMovie WHERE id =:id")
    suspend fun getTMDbMovieByID(id: String): TMDbCachedRoomMovie

    @Query("SELECT * FROM tmdbMovie WHERE title LIKE :titleToSearchFor")
    suspend fun getTMDbMoviesForTitle(titleToSearchFor: String): List<TMDbCachedRoomMovie>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllSuspend(entities: List<TMDbCachedRoomMovie>)
}