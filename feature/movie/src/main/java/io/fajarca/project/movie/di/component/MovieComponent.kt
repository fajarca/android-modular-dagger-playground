package io.fajarca.project.movie.di.component

import dagger.Component
import io.fajarca.project.daggerplayground.di.module.MovieModuleDependencies
import io.fajarca.project.movie.di.module.NetworkModule
import io.fajarca.project.movie.di.module.RepositoryModule
import io.fajarca.project.movie.presentation.MovieListActivity

@Component(
    dependencies = [MovieModuleDependencies::class],
    modules = [
        NetworkModule::class,
        RepositoryModule::class
    ]
)
interface MovieComponent {
    fun inject(activity: MovieListActivity)

    @Component.Builder
    interface Builder {
        fun appDependencies(movieModuleDependencies: MovieModuleDependencies): Builder
        fun build(): MovieComponent
    }
}