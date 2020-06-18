package com.example.cache.tmdbmovies.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.example.cache.tmdbmovies.model.RoomCast

@Dao
interface TMDbCastDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun InserCast(entities: List<RoomCast>?)
}