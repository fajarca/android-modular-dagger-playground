package io.fajarca.project.movie.di.module

import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import dagger.hilt.migration.DisableInstallInCheck
import io.fajarca.project.core.factory.ViewModelFactory

@Module
@DisableInstallInCheck
abstract class ViewModelFactoryModule {
    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}