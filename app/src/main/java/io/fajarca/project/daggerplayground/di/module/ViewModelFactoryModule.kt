package io.fajarca.project.daggerplayground.di.module

import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import io.fajarca.project.daggerplayground.di.ViewModelFactory

@Module
abstract class ViewModelFactoryModule {
    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}