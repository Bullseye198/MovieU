package com.example.remote.tmdbmovie.model.tvlist


import com.example.domain.tmdbmovie.model.tvlist.TMDbTvList
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class TMDbTvListRaw(
    @Json(name = "page")
    val page: Int, // 1
    @Json(name = "results")
    val resultsRaw: List<ResultRaw>,
    @Json(name = "total_pages")
    val totalPages: Int, // 1
    @Json(name = "total_results")
    val totalResults: Int // 1
)

fun TMDbTvListRaw.mapToDomainTMDbTvList() = TMDbTvList(
    page = page,
    results = resultsRaw.map { it.mapToDomainResult() },
    totalPages = totalPages,
    totalResults = totalResults
)