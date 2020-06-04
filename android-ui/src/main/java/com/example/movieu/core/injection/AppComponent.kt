package com.example.movieu.core.injection

import android.content.Context
import com.cm.baseAndroid.injection.ThreadingModule
import com.example.cache.injection.CacheModule
import com.example.cache.injection.CacheBindsModule
import com.example.data.injection.DataModule
import com.example.movieu.MyApplication
import com.example.movieu.core.injection.module.ApplicationModule
import com.example.movieu.core.injection.module.ApplicationModuleBinds
import com.example.movieu.core.injection.module.DaoModule
import com.example.movieu.dependencyInjection.AppDataModule
import com.example.movieu.dependencyInjection.MovieUIModule
import com.example.movieu.dependencyInjection.ViewModelModule
import com.example.remote.movie.RemoteModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        ApplicationModuleBinds::class,
        ViewModelModule::class,
        AndroidInjectionModule::class,
        ApplicationModule::class,
        MovieUIModule::class,
        DaoModule::class,
        ApplicationModule::class,
        AppDataModule::class,
        RemoteModule::class,
        DataModule::class,
        CacheModule::class,
        CacheBindsModule::class,
        ThreadingModule::class
    ]
)

interface AppComponent : AndroidInjector<MyApplication> {
    @Component.Factory
    interface Factory {
        fun create(@BindsInstance applicationContext: Context): AppComponent
    }
}