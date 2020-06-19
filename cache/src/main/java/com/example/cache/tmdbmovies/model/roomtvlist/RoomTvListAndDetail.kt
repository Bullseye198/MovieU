package com.example.cache.tmdbmovies.model.roomtvlist

import androidx.room.Embedded
import androidx.room.Relation
import com.example.cache.tmdbmovies.model.roomtvdetail.*

data class RoomTvListAndDetail(
    @Embedded
    var roomTvListResult: RoomTvListResult,
    @Relation(parentColumn = "id", entityColumn = "createdByID")
    var roomTvDetailCreatedBy: RoomTvDetailCreatedBy,
    @Relation(parentColumn = "id", entityColumn = "genreID")
    var roomTvDetailGenre: RoomTvDetailGenre,
    @Relation(parentColumn = "id", entityColumn = "lastEpisodeToAirID")
    var roomTvDetailLastEpisodeToAir: RoomTvDetailLastEpisodeToAir,
    @Relation(parentColumn = "id", entityColumn = "networkID")
    var roomTvDetailNetwork: RoomTvDetailNetwork,
    @Relation(parentColumn = "id", entityColumn = "productionCompanyID")
    var roomTvDetailProductionCompany: RoomTvDetailProductionCompany,
    @Relation(parentColumn = "id", entityColumn = "seasonID")
    var roomTvDetailSeason: RoomTvDetailSeason
)