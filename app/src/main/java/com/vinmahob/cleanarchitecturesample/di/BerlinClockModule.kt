package com.vinmahob.cleanarchitecturesample.di

import com.berlin.uhr.domain.BerlinClockUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object BerlinClockModule {
    @Provides
    fun provideBerlinClockUseCase() = BerlinClockUseCase()
}