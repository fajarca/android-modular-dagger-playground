package io.fajarca.project.movie.di.component

import dagger.Component
import io.fajarca.project.apiclient.di.ApiClientComponent
import io.fajarca.project.base.di.component.BaseComponent
import io.fajarca.project.base.di.scope.ModuleScope
import io.fajarca.project.movie.di.module.RepositoryModule
import io.fajarca.project.movie.di.module.SubcomponentModule
import io.fajarca.project.movie.di.module.ViewModelFactoryModule
import io.fajarca.project.movie.di.module.NetworkModule
import io.fajarca.project.movie.presentation.di.MovieListActivityComponent

@ModuleScope
@Component(
    dependencies = [BaseComponent::class, ApiClientComponent::class],
    modules = [
        NetworkModule::class,
        ViewModelFactoryModule::class,
        RepositoryModule::class,
        SubcomponentModule::class
    ]
)
interface MovieComponent {
    fun movieListActivityComponent() : MovieListActivityComponent.Factory
}