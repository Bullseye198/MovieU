package com.example.cache.tmdbmovies

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.cache.tmdbmovies.dao.*
import com.example.cache.tmdbmovies.dao.tvdetaildao.*
import com.example.cache.tmdbmovies.model.*
import com.example.cache.tmdbmovies.model.roomtvdetail.*
import com.example.cache.tmdbmovies.model.roomtvlist.RoomTvListResult

private const val DATABASE = "tmdbMovie"

@Database(
    entities = [TMDbCachedRoomResultFull::class,
        RoomGenre::class,
        RoomSpokenLanguage::class,
        RoomCast::class,
        RoomCrew::class,
        RoomTvListResult::class,
        RoomTvDetailCreatedBy::class,
        RoomTvDetailGenre::class,
        RoomTvDetailLastEpisodeToAir::class,
        RoomTvDetailNetwork::class,
        RoomTvDetailProductionCompany::class,
        RoomTvDetailSeason::class
    ],
    version = 5,
    exportSchema = false
)

abstract class TMDbRoomMovieDatabase : RoomDatabase() {
    abstract fun tmdbRoomMovieDao(): TMDbMovieDao
    abstract fun tmdbRoomGenreDao(): TMDbGenreDao
    abstract fun tmdbRoomSpokenLanguageDao(): TMDbSpokenLanguageDao
    abstract fun tmdbRoomCastDao(): TMDbCastDao
    abstract fun tmdbRoomCrewDao(): TMDbCrewDao
    abstract fun tvDetailCreatedByDao(): TvDetailCreatedByDao
    abstract fun tvDetailGenreDao(): TvDetailGenreDao
    abstract fun tvDetailLastEpisodeToAirDao(): TvDetailLastEpisodeToAirDao
    abstract fun tvDetailNetworkDao(): TvDetailNetworkDao
    abstract fun tvDetailProductionCompanyDao(): TvDetailProductionCompanyDao
    abstract fun tvDetailSeasonDao(): TvDetailSeasonDao
}