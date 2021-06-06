package io.fajarca.project.user.presentation.main.di

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
abstract class UserActivityModule {

    @Binds
    @IntoMap
    @ViewModelKey(UserViewModel::class)
    internal abstract fun providesUserViewModel(viewModel: UserViewModel): ViewModel

}
