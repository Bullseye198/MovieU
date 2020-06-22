package com.example.cache.tmdbmovies.model.roomtvlist

import androidx.room.Embedded
import androidx.room.Relation
import com.example.cache.tmdbmovies.model.roomtvdetail.*
import com.example.domain.tmdbmovie.model.tvdetail.TMDbTvDetail

data class RoomTvListAndDetail(
    @Embedded
    var roomTvListResult: RoomTvListResult,
    @Relation(parentColumn = "id", entityColumn = "createdByID")
    var roomTvDetailCreatedBy: List<RoomTvDetailCreatedBy>,
    @Relation(parentColumn = "id", entityColumn = "genreID")
    var roomTvDetailGenre: List<RoomTvDetailGenre>,
    @Relation(parentColumn = "id", entityColumn = "lastEpisodeToAirID")
    var roomTvDetailLastEpisodeToAir: RoomTvDetailLastEpisodeToAir?,
    @Relation(parentColumn = "id", entityColumn = "networkID")
    var roomTvDetailNetwork: List<RoomTvDetailNetwork>,
    @Relation(parentColumn = "id", entityColumn = "productionCompanyID")
    var roomTvDetailProductionCompany: List<RoomTvDetailProductionCompany>,
    @Relation(parentColumn = "id", entityColumn = "seasonID")
    var roomTvDetailSeason: List<RoomTvDetailSeason>,
    @Relation(parentColumn = "id", entityColumn = "languagesID")
    var roomTvDetailLanguages: List<RoomTvDetailLanguages>
)

fun RoomTvListAndDetail.mapToDomainTvDetailResult(): TMDbTvDetail {
    return roomTvListResult.mapToDomainTMDbTvDetail(
        roomTvDetailCreatedBy,
        roomTvDetailGenre,
        roomTvDetailLastEpisodeToAir,
        roomTvDetailNetwork,
        roomTvDetailProductionCompany,
        roomTvDetailSeason,
        roomTvDetailLanguages
    )
}