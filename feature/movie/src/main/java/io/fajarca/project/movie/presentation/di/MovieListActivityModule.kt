package io.fajarca.project.movie.presentation.di

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import io.fajarca.project.base.di.annotation.ViewModelKey
import io.fajarca.project.movie.presentation.MovieListViewModel


@Module
abstract class MovieListActivityModule {

    @Binds
    @IntoMap
    @ViewModelKey(MovieListViewModel::class)
    internal abstract fun providesMovieListViewModel(viewModel: MovieListViewModel): ViewModel

}
