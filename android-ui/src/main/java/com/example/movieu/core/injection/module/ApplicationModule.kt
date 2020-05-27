package com.example.movieu.core.injection.module

import android.content.Context
import androidx.room.Room
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.cm.base.executor.AppCoroutineDispatchers
import com.example.cache.movies.RoomMovieDatabase
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.Dispatchers
import okhttp3.Interceptor
import javax.inject.Singleton

@Module
object ApplicationModule {

    @Singleton
    @Provides
        fun provideRoomDatabase(
        applicationContext: Context
    ) : RoomMovieDatabase {
        return Room.databaseBuilder(applicationContext, RoomMovieDatabase::class.java, "moviedagger")
            .fallbackToDestructiveMigration()
            .build()
    }


    @Provides
    @Singleton
    fun provideChuckerInterceptor(
        context: Context
    ): Interceptor{
        return ChuckerInterceptor(context)
    }

}