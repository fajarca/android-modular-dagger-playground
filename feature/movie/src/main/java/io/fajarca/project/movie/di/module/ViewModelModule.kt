package io.fajarca.project.movie.di.module

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import io.fajarca.project.core.di.annotation.ViewModelKey
import io.fajarca.project.movie.presentation.MovieListViewModel


@Module
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(MovieListViewModel::class)
    internal abstract fun providesMovieListViewModel(viewModel: MovieListViewModel): ViewModel

}
