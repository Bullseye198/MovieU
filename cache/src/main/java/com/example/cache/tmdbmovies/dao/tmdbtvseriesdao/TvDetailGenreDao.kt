package com.example.cache.tmdbmovies.dao.tmdbtvseriesdao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.example.cache.tmdbmovies.model.roomtvdetail.RoomTvDetailGenre

@Dao
interface TvDetailGenreDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTvDetailGenre(entities: List<RoomTvDetailGenre>)
}