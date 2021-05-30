package io.fajarca.project.daggerplayground.di.module

import androidx.lifecycle.ViewModelProvider
import io.fajarca.project.daggerplayground.di.factory.ViewModelFactory
import dagger.Binds
import dagger.Module

@Module
abstract class ViewModelFactoryModule {
    @Binds
    internal abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}