package com.example.movieu.core.injection.module

import android.content.Context
import androidx.room.Room
import com.example.cache.movies.RoomMovieDatabase
import dagger.Module
import dagger.Provides
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


}