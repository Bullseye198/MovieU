package com.example.remote.movie

import com.example.remote.movie.model.OMDbMovieDetailRaw
import com.example.remote.movie.model.OMDbSearchResults
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Defines the abstract methods used for interacting with the Movie Service.
 */

//"http://www.omdbapi.com/?t=avengers+endgame&y=2019&plot=full&apikey=3e6675f5"

interface MovieService {

    @GET("?")
    suspend fun getMovieDetail(
        @Query("i") imdbID: String,
        @Query("apikey") apikey: String
    ): OMDbMovieDetailRaw
}