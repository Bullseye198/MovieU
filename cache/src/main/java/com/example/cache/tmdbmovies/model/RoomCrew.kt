package com.example.cache.tmdbmovies.model

import androidx.room.Entity
import com.example.domain.tmdbmovie.model.Crew

@Entity(
    tableName = "crew",
    primaryKeys = ["id", "crewTMDbID"]
)

data class RoomCrew(
    val id: Int, // 123
    val crewTMDbID: String,
    val creditId: String, // 5831cc6d92514162d2027340
    val department: String, // Production
    val gender: Int, // 2
    val job: String, // Executive Producer
    val name: String, // Barrie M. Osborne
    val profilePath: String? // /xWtXYk6M5NFroddcQDviLlxOnkU.jpg
)

fun RoomCrew.mapToDomainCrew(): Crew {
    return Crew(
        creditId = creditId,
        department = department,
        gender = gender,
        id = id,
        job = job,
        name = name,
        profilePath = profilePath
    )
}

fun Crew.mapToRoomCrew(crewTMDbID: String): RoomCrew{
    return RoomCrew(
        creditId = creditId,
        department = department,
        gender = gender,
        id = id,
        job = job,
        name = name,
        profilePath = profilePath,
        crewTMDbID = crewTMDbID
    )
}