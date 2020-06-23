package com.example.movieu.movie.moviedetail

import com.example.domain.movie.model.OMDbBaseInformation
import com.example.domain.tmdbmovie.model.UIMediaDetail

data class MediaDetailState(
    val OMDbBaseInformation: OMDbBaseInformation? = null,
    val uiMediaDetail: UIMediaDetail? = null
)
