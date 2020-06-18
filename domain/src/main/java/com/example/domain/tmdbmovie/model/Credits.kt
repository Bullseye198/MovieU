package com.example.domain.tmdbmovie.model

import com.squareup.moshi.Json

data class Credits(
    val cast: List<Cast>,
    val crew: List<Crew>,
    val id: Int // 603
)