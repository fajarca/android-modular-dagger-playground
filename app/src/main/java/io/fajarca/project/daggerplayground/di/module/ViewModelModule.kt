package io.fajarca.project.daggerplayground.di.module

import androidx.lifecycle.ViewModel
import com.dafturn.mypertamina.di.annotation.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import io.fajarca.project.daggerplayground.main.MainViewModel

@Module
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    internal abstract fun provideMainViewModel(viewModel: MainViewModel): ViewModel


}