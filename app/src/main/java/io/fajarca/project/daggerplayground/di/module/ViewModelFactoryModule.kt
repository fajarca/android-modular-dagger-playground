package io.fajarca.project.daggerplayground.di.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import io.fajarca.project.daggerplayground.di.factory.ViewModelFactory
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import io.fajarca.project.daggerplayground.di.annotation.ViewModelKey
import io.fajarca.project.daggerplayground.login.LoginViewModel

@Module
abstract class ViewModelFactoryModule {
    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(LoginViewModel::class)
    internal abstract fun providesLoginViewModel(viewModel: LoginViewModel): ViewModel
}