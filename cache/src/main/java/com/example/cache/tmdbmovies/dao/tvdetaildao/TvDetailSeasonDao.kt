package com.example.cache.tmdbmovies.dao.tvdetaildao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.example.cache.tmdbmovies.model.roomtvdetail.RoomTvDetailCreatedBy
import com.example.cache.tmdbmovies.model.roomtvdetail.RoomTvDetailSeason

@Dao
interface TvDetailSeasonDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTvDetailSeason(entities: List<RoomTvDetailSeason>)
}