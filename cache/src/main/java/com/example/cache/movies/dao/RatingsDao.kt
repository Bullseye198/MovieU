package com.example.cache.movies.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.example.cache.movies.model.RoomRatings

@Dao
interface RatingsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun InsertRatings(entities: List<RoomRatings>?)
}