package io.fajarca.project.daggerplayground.login.di

import dagger.Subcomponent
import io.fajarca.project.daggerplayground.di.scope.ActivityScope
import io.fajarca.project.daggerplayground.login.presentation.LoginActivity

@ActivityScope
@Subcomponent
interface LoginComponent {

    @Subcomponent.Factory
    interface Factory {
        fun create(): LoginComponent
    }

    fun inject(activity : LoginActivity)
}
