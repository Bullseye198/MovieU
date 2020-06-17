package com.example.movieu.core.injection.module

import android.content.Context
import androidx.room.Room
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.example.cache.tmdbmovies.TMDbRoomMovieDatabase
import dagger.Module
import dagger.Provides
import okhttp3.Interceptor
import javax.inject.Singleton

@Module
object ApplicationModule {

    @Singleton
    @Provides
    fun provideTMDbRoomDatabase(
        applicationContext: Context
    ): TMDbRoomMovieDatabase {
        return Room.databaseBuilder(
            applicationContext,
            TMDbRoomMovieDatabase::class.java,
            "tmdbmoviedagger"
        )
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun provideChuckerInterceptor(
        context: Context
    ): Interceptor {
        return ChuckerInterceptor(context)
    }
}