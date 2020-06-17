package com.example.data.movie

import com.example.domain.movie.model.OMDbBaseInformation

interface MovieRemote {

    suspend fun fetchMovieDetail(imdbID: String): OMDbBaseInformation
}