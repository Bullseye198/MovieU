package com.example.cache.tmdbmovies.dao.tmdbtvseriesdao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.cache.tmdbmovies.model.roomtvlist.RoomTvListAndDetail
import com.example.cache.tmdbmovies.model.roomtvlist.RoomTvListResult
import kotlinx.coroutines.flow.Flow

@Dao
interface TMDbTvSeriesDao {

    //@Query("SELECT * FROM tmdbTv")
    //fun observeTMDbTvList(): Flowable<List<RoomTvListResult>>

    @Query("SELECT * FROM tmdbTv WHERE name LIKE :nameToSearchFor")
    fun observeTMDbTvListForName(nameToSearchFor: String): Flow<List<RoomTvListResult>>

    @Query("SELECT * FROM tmdbTv WHERE id =:id")
    fun observeTMDbTvDetail(id: Int): Flow<RoomTvListAndDetail>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTvAllSuspend(entities: List<RoomTvListResult>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOneSuspend(entity: RoomTvListResult)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAllIgnore(entities: List<RoomTvListResult>)
}