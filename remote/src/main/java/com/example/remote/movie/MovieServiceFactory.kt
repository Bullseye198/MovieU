package com.example.remote.movie

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

object MovieServiceFactory {

    fun makeMovieService(chuckerInterceptor: Interceptor): MovieService {

        val retrofit = Retrofit.Builder()
            .baseUrl("http://www.omdbapi.com/")
            .addConverterFactory(MoshiConverterFactory.create())
            .client(
                makeOkHttpClient(
                    chuckerInterceptor
                )
            )
            .build()

        return retrofit.create(MovieService::class.java)

    }


    private fun makeOkHttpClient(
        chuckerInterceptor: Interceptor
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(chuckerInterceptor)
            .connectTimeout(120, TimeUnit.SECONDS)
            .readTimeout(120, TimeUnit.SECONDS)
            .build()
    }
}