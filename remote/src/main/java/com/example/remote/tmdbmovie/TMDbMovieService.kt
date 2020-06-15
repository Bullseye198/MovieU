package com.example.remote.tmdbmovie

import com.example.remote.tmdbmovie.model.TMDbMovieDetailRaw
import com.example.remote.tmdbmovie.model.TMDbMovieSearchResultsRaw
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import javax.jws.WebService

//Get TMDb movie list url:
//"https://api.themoviedb.org/3/search/movie?api_key=2797198b75a6557cae56bdfdb2dd1b52&query=Jurassic"

//Get TMDb movie detail url:
//"https://api.themoviedb.org/3/movie/135397?api_key=2797198b75a6557cae56bdfdb2dd1b52"

interface TMDbMovieService {

    @GET("search/movie")
    suspend fun getCurrentMovieList(
        @Query("query") tmdbTitleToSearchFor: String,
        @Query("api_key") apikey: String
    ): TMDbMovieSearchResultsRaw

    @GET("movie/")
    suspend fun getTMDbMovieDetail(
        @Query("") id: Int,
        @Query("api_key") apikey: String
    ): TMDbMovieDetailRaw

}
