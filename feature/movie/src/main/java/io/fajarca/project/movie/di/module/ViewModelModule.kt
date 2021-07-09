package io.fajarca.project.movie.di.module

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.hilt.migration.DisableInstallInCheck
import dagger.multibindings.IntoMap
import io.fajarca.project.base.di.annotation.ViewModelKey
import io.fajarca.project.movie.presentation.MovieListViewModel


@Module
@DisableInstallInCheck
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(MovieListViewModel::class)
    internal abstract fun providesMovieListViewModel(viewModel: MovieListViewModel): ViewModel

}
