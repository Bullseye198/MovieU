package com.example.cache.tmdbmovies.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.example.cache.tmdbmovies.model.RoomGenre

@Dao
interface TMDbGenreDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun InsertGenre(entities: List<RoomGenre>?)
}