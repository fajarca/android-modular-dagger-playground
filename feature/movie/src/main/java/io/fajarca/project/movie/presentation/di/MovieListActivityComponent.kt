package io.fajarca.project.movie.presentation.di

import dagger.Subcomponent
import io.fajarca.project.base.di.scope.FeatureScope
import io.fajarca.project.movie.presentation.MovieListActivity

@FeatureScope
@Subcomponent(
    modules = [
        MovieListActivityModule::class
    ]
)
interface MovieListActivityComponent {
    @Subcomponent.Factory
    interface Factory {
        fun create(): MovieListActivityComponent
    }
    fun inject(activity: MovieListActivity)
}