package com.example.movieu.movie.moviedetail

import com.example.domain.movie.model.OMDbBaseInformation

data class MovieDetailState(
    val OMDbBaseInformation: OMDbBaseInformation? = null
)