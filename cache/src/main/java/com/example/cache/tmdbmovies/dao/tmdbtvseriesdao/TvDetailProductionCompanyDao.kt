package com.example.cache.tmdbmovies.dao.tmdbtvseriesdao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.example.cache.tmdbmovies.model.roomtvdetail.RoomTvDetailProductionCompany

@Dao
interface TvDetailProductionCompanyDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTvDetailProductionCompany(entities: List<RoomTvDetailProductionCompany>)
}