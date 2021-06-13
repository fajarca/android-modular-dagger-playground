package io.fajarca.project.movie.di.module

import dagger.Binds
import dagger.Module
import io.fajarca.project.movie.data.repository.MovieRepositoryImpl
import io.fajarca.project.movie.domain.repository.MovieRepository

@Module
interface RepositoryModule {
    @Binds
    fun bindMovieRepositoryImpl(repository: MovieRepositoryImpl): MovieRepository
}