package io.fajarca.project.user.presentation.detail.di

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import io.fajarca.project.base.di.annotation.ViewModelKey
import io.fajarca.project.user.presentation.detail.UserDetailViewModel

@Module
abstract class UserDetailModule {

    @Binds
    @IntoMap
    @ViewModelKey(UserDetailViewModel::class)
    internal abstract fun providesUserDetailViewModel(viewModel: UserDetailViewModel): ViewModel

}
