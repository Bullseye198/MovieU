package com.example.movieu.dependencyInjection

import dagger.Binds
import dagger.Module
import kotlin.coroutines.CoroutineContext

@Module
interface AppDataModule {

    @Binds
    fun bindCoroutineContext(coroutineContext: CoroutineContext): CoroutineContext
}