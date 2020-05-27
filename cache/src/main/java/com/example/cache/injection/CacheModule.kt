package com.example.cache.injection

import com.example.cache.injection.CacheBindsModule
import dagger.Module

@Module(
    includes = [
    CacheBindsModule::class]
)
object CacheModule {
}