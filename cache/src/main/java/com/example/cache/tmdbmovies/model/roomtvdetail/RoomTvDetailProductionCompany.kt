package com.example.cache.tmdbmovies.model.roomtvdetail

import androidx.room.Entity
import com.example.domain.tmdbmovie.model.tvdetail.TvDetailProductionCompany

@Entity(
    tableName = "tvdetailproductioncompany",
    primaryKeys = ["id", "productionCompanyID"]
)
data class RoomTvDetailProductionCompany(
    val id: Int, // 1957
    val productionCompanyID: String,
    val logoPath: String, // /nmcNfPq03WLtOyufJzQbiPu2Enc.png
    val name: String, // Warner Bros. Television
    val originCountry: String // US
)

fun RoomTvDetailProductionCompany.mapToDomainTvDetailProductionCompany(): TvDetailProductionCompany {
    return TvDetailProductionCompany(
        id = id,
        logoPath = logoPath,
        name = name,
        originCountry = originCountry
    )
}

fun TvDetailProductionCompany.mapToRoomTvDetailProductionCompany(productionCompanyID: String): RoomTvDetailProductionCompany {
    return RoomTvDetailProductionCompany(
        id = id,
        logoPath = logoPath,
        name = name,
        originCountry = originCountry,
        productionCompanyID = productionCompanyID
    )
}

