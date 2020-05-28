package com.example.cache.movies.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.cache.movies.RoomMovie
import io.reactivex.Flowable

@Dao
interface MovieDao {
    @Query("SELECT * FROM movie")
    suspend fun getMovies(): List<RoomMovie>

    @Query("SELECT * FROM movie WHERE title LIKE :titleToSearchFor")
    suspend fun getMoviesForTitle(titleToSearchFor: String): List<RoomMovie>

  /*  @Query("SELECT * FROM movie")
    suspend fun observeMovies(): Flowable<List<RoomMovie>>
*/
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllSuspend(entities: List<RoomMovie>)
}

