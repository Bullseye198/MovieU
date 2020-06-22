package com.example.cache.tmdbmovies.dao.tmdbmoviesdao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.example.cache.tmdbmovies.model.roommoviedetail.RoomCast

@Dao
interface TMDbCastDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun InsertCast(entities: List<RoomCast>?)
}