package io.fajarca.project.user.presentation.detail.di

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import io.fajarca.project.base.di.scope.ModuleScope
import io.fajarca.project.base.di.annotation.ViewModelKey
import io.fajarca.project.base.di.scope.FeatureScope
import io.fajarca.project.user.presentation.detail.UserDetailViewModel
import io.fajarca.project.user.presentation.main.UserViewModel

@Module
abstract class UserDetailModule {

    @Binds
    @IntoMap
    @ViewModelKey(UserDetailViewModel::class)
    internal abstract fun providesUserDetailViewModel(viewModel: UserDetailViewModel): ViewModel

}
