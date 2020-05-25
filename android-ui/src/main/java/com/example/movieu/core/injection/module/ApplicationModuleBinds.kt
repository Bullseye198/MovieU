package com.example.movieu.core.injection.module

import com.example.movieu.dependencyInjection.MovieUIModule
import com.example.movieu.features.main.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
interface ApplicationModuleBinds {

    @ContributesAndroidInjector(
        modules = [MovieUIModule::class]
    )
    fun contributeMainActivity(): MainActivity
}