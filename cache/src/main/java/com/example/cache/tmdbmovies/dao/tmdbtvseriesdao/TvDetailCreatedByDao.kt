package com.example.cache.tmdbmovies.dao.tmdbtvseriesdao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.example.cache.tmdbmovies.model.roomtvdetail.RoomTvDetailCreatedBy

@Dao
interface TvDetailCreatedByDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTvDetailCreatedBy(entities: List<RoomTvDetailCreatedBy>)
}