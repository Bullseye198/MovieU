package com.example.cache.tmdbmovies.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.example.cache.tmdbmovies.model.RoomSpokenLanguage

@Dao
interface TMDbSpokenLanguageDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun InsertSpokenLanguage(entities: List<RoomSpokenLanguage>?)
}