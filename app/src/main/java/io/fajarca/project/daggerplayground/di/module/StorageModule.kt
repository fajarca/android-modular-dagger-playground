package io.fajarca.project.daggerplayground.di.module

import dagger.Binds
import dagger.Module
import io.fajarca.project.base.abstraction.Storage
import io.fajarca.project.daggerplayground.data.SharedPreferenceStorage

@Module
abstract class StorageModule {

    @Binds
    abstract fun provideStorage(storage: SharedPreferenceStorage): Storage
}