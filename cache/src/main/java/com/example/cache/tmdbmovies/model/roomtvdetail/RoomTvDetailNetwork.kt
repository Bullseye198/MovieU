package com.example.cache.tmdbmovies.model.roomtvdetail

import androidx.room.Entity
import com.example.domain.tmdbmovie.model.tvdetail.TvDetailNetwork

@Entity(
    tableName = "tvdetailnetwork",
    primaryKeys = ["id", "networkID"]
)

data class RoomTvDetailNetwork(
    val id: Int, // 71,
    val networkID: String,
    val logoPath: String, // /ge9hzeaU7nMtQ4PjkFlc68dGAJ9.png
    val name: String, // The CW
    val originCountry: String // US
)

fun RoomTvDetailNetwork.mapToDomainTvDetailNetwork(): TvDetailNetwork {
    return TvDetailNetwork(
        id = id,
        logoPath = logoPath,
        name = name,
        originCountry = originCountry
    )
}

fun TvDetailNetwork.mapToRoomTvDetailNetwork(networkID: String) : RoomTvDetailNetwork {
    return RoomTvDetailNetwork(
        id = id,
        logoPath = logoPath,
        name = name,
        originCountry = originCountry,
        networkID = networkID
    )
}