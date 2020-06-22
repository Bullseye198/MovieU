package com.example.cache.tmdbmovies.dao.tmdbmoviesdao

import androidx.room.*
import com.example.cache.tmdbmovies.model.roommovielist.TMDbCachedRoomResultFull
import com.example.cache.tmdbmovies.model.roommovielist.TMDbMovieGenresSpokenLanguagesCastAndCrew
import io.reactivex.Flowable

@Dao
interface TMDbMovieDao {

    @Query("SELECT * FROM tmdbMovie")
    suspend fun getTMDbMovies(): List<TMDbCachedRoomResultFull>


    @Query("SELECT * FROM tmdbMovie WHERE title LIKE :titleToSearchFor")
    suspend fun getTMDbMoviesForTitle(titleToSearchFor: String): List<TMDbCachedRoomResultFull>

    @Query("SELECT * FROM tmdbMovie WHERE id =:id")
    suspend fun getTMDbMovieByID(id: Int): TMDbCachedRoomResultFull

    @Query("SELECT * FROM tmdbMovie")
    fun observeTMDbMovies(): Flowable<List<TMDbCachedRoomResultFull>>

    @Query("SELECT * FROM tmdbMovie WHERE id =:id")
    fun observeTMDbMovieDetail(id: Int): Flowable<TMDbMovieGenresSpokenLanguagesCastAndCrew>

    @Query("UPDATE tmdbMovie SET imdRating = :imdbRating WHERE imdbID =:imdbID ")
    fun updateTMDbMovie(imdbID: String, imdbRating: String?)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllSuspend(entities: List<TMDbCachedRoomResultFull>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOneSuspend(entity: TMDbCachedRoomResultFull)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAllIgnore(entities: List<TMDbCachedRoomResultFull>)
}