package io.fajarca.project.movie.di.module

import dagger.Module
import io.fajarca.project.movie.presentation.di.MovieListActivityComponent

@Module(
    subcomponents = [
        MovieListActivityComponent::class,
    ]
)
class SubcomponentModule