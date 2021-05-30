package io.fajarca.project.daggerplayground.login.di

import androidx.lifecycle.ViewModel
import com.dafturn.mypertamina.di.annotation.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class LoginViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(LoginViewModel::class)
    internal abstract fun providesLoginViewModel(viewModel: LoginViewModel): ViewModel
}