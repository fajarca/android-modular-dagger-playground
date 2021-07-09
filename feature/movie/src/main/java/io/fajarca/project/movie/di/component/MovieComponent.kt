package io.fajarca.project.movie.di.component

import dagger.Component
import io.fajarca.project.daggerplayground.di.dependencies.MovieModuleDependencies
import io.fajarca.project.movie.di.module.NetworkModule
import io.fajarca.project.movie.di.module.RepositoryModule
import io.fajarca.project.movie.di.module.ViewModelFactoryModule
import io.fajarca.project.movie.di.module.ViewModelModule
import io.fajarca.project.movie.presentation.MovieListActivity

@Component(
    dependencies = [MovieModuleDependencies::class],
    modules = [
        NetworkModule::class,
        RepositoryModule::class,
        ViewModelFactoryModule::class,
        ViewModelModule::class
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