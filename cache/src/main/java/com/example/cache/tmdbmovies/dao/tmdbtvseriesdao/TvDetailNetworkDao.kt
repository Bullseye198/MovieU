package com.example.cache.tmdbmovies.dao.tmdbtvseriesdao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.example.cache.tmdbmovies.model.roomtvdetail.RoomTvDetailNetwork

@Dao
interface TvDetailNetworkDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTvDetailNetwork(entities: List<RoomTvDetailNetwork>)
}
