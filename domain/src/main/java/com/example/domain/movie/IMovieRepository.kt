package com.example.domain.movie

import com.example.domain.movie.model.OMDbBaseInformation

interface IMovieRepository {

    suspend fun fetchMovieDetail(imdbID: String): OMDbBaseInformation
}