package com.example.remote.tmdbmovie

import com.example.remote.movie.MovieService
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

object TMDbMovieServiceFactory {

    fun makeTMDbMovieService(chuckerInterceptor: Interceptor): TMDbMovieService {

        val retrofit = Retrofit.Builder()
            .baseUrl("http://api.themoviedb.org/3/")
            .addConverterFactory(MoshiConverterFactory.create())
            .client(
                makeOkHttpClient(
                    chuckerInterceptor
                )
            )
            .build()

        return retrofit.create(TMDbMovieService::class.java)
    }

    private fun makeOkHttpClient(chuckerInterceptor: Interceptor): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(chuckerInterceptor)
            .connectTimeout(120, TimeUnit.SECONDS)
            .readTimeout(120, TimeUnit.SECONDS)
            .build()
    }
}