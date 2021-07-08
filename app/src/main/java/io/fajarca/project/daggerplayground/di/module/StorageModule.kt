package io.fajarca.project.daggerplayground.di.module

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.fajarca.project.base.abstraction.Storage
import io.fajarca.project.daggerplayground.data.SharedPreferenceStorage

@Module
@InstallIn(SingletonComponent::class)
interface StorageModule {
    @Binds
    fun provideStorage(storage: SharedPreferenceStorage): Storage
}