package com.example.movieu.movie.moviedetail

import com.example.domain.movie.model.OMDbBaseInformation
import com.example.domain.tmdbmovie.model.TMDbMovieDetail

data class MovieDetailState(
    val OMDbBaseInformation: OMDbBaseInformation? = null,
    val tmDbMovieDetail: TMDbMovieDetail? = null
)