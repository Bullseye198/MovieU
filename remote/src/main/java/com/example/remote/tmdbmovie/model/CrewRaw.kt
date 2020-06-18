package com.example.remote.tmdbmovie.model


import com.example.domain.tmdbmovie.model.Crew
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CrewRaw(
    @Json(name = "credit_id")
    val creditId: String, // 5831cc6d92514162d2027340
    @Json(name = "department")
    val department: String, // Production
    @Json(name = "gender")
    val gender: Int, // 2
    @Json(name = "id")
    val id: Int, // 123
    @Json(name = "job")
    val job: String, // Executive Producer
    @Json(name = "name")
    val name: String, // Barrie M. Osborne
    @Json(name = "profile_path")
    val profilePath: String? // /xWtXYk6M5NFroddcQDviLlxOnkU.jpg
)

fun CrewRaw.mapDomainCrewModel() = Crew(
    creditId = creditId,
    department = department,
    gender = gender,
    id = id,
    job = job,
    name = name,
    profilePath = profilePath
)