package com.pants.cleannotes.di

import com.pants.cleannotes.util.DispatcherProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object DispatcherModule {

    @Provides
    fun providerDispatcherProvider(): DispatcherProvider = DispatcherProvider()
}