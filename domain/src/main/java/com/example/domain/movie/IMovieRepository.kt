package com.example.domain.movie

import com.example.domain.movie.model.Movie
import com.example.domain.movie.model.OMDbBaseInformation
import io.reactivex.Flowable

interface IMovieRepository {

    suspend fun fetchMovieDetail(imdbID: String): OMDbBaseInformation

}