package com.example.cache.tmdbmovies.dao.tmdbtvseriesdao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.example.cache.tmdbmovies.model.roomtvdetail.RoomTvDetailLastEpisodeToAir

@Dao
interface TvDetailLastEpisodeToAirDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTvDetailLastEpisodeToAir(entities: RoomTvDetailLastEpisodeToAir?)
}