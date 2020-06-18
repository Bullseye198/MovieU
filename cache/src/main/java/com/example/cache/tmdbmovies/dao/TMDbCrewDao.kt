package com.example.cache.tmdbmovies.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.example.cache.tmdbmovies.model.RoomCrew

@Dao
interface TMDbCrewDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun InserCrew(entitites: List<RoomCrew>)
}