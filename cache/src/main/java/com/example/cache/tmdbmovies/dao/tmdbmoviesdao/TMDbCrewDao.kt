package com.example.cache.tmdbmovies.dao.tmdbmoviesdao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.example.cache.tmdbmovies.model.RoomCrew

@Dao
interface TMDbCrewDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun InsertCrew(entitites: List<RoomCrew>?)
}