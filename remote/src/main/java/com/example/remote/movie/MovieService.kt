package com.example.remote.movie

import com.example.remote.movie.model.MovieDetailRaw
import com.example.remote.movie.model.Movies
import com.example.remote.movie.model.SearchResults
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Defines the abstract methods used for interacting with the Movie Service.
 */

//"http://www.omdbapi.com/?t=avengers+endgame&y=2019&plot=full&apikey=3e6675f5"

interface MovieService {

    @GET("?")
    suspend fun getCurrentMovie(
        @Query("s") titleToSearchFor: String,
        @Query("apikey") apikey: String
        ): SearchResults

    @GET("?")
    suspend fun getMovieDetail(
        @Query("i") imdbID: String,
        @Query("apikey") apikey: String
    ): MovieDetailRaw
}