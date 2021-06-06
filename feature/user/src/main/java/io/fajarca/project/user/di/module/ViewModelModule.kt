package io.fajarca.project.user.di.module

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import io.fajarca.project.base.di.scope.ModuleScope
import io.fajarca.project.base.di.annotation.ViewModelKey
import io.fajarca.project.user.presentation.main.UserViewModel

@Module
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(UserViewModel::class)
    @ModuleScope
    internal abstract fun providesUserViewModel(viewModel: UserViewModel): ViewModel

}
