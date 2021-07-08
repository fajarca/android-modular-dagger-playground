package io.fajarca.project.movie.di.module

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.scopes.ActivityScoped
import io.fajarca.project.movie.data.repository.MovieRepositoryImpl
import io.fajarca.project.movie.domain.repository.MovieRepository

@Module
@InstallIn(ActivityComponent::class)
interface RepositoryModule {
    @Binds
    fun bindMovieRepositoryImpl(repository: MovieRepositoryImpl): MovieRepository
}