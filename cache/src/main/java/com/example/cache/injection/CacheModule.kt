package com.example.cache.injection

import dagger.Module

@Module(
    includes = [
        CacheBindsModule::class]
)
object CacheModule {
}