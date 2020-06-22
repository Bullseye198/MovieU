package com.example.movieu.movie.tvdetail

import com.example.domain.movie.model.OMDbBaseInformation
import com.example.domain.tmdbmovie.model.UIMediaDetail
import com.example.domain.tmdbmovie.model.moviedetail.TMDbMovieDetail
import com.example.domain.tmdbmovie.model.tvdetail.TMDbTvDetail

data class MediaDetailState(
    val OMDbBaseInformation: OMDbBaseInformation? = null,
    val uiMediaDetail: UIMediaDetail? = null
)
