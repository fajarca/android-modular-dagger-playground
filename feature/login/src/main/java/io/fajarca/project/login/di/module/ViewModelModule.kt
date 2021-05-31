package io.fajarca.project.login.di.module

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import io.fajarca.project.base.ModuleScope
import io.fajarca.project.login.di.annotation.ViewModelKey
import io.fajarca.project.login.presentation.LoginViewModel

@Module
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(LoginViewModel::class)
    @ModuleScope
    internal abstract fun providesLoginViewModel(viewModel: LoginViewModel): ViewModel

}
