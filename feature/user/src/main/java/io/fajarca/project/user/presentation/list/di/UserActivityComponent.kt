package io.fajarca.project.user.presentation.list.di

import dagger.Subcomponent
import io.fajarca.project.base.di.scope.FeatureScope
import io.fajarca.project.user.presentation.list.UserActivity

@FeatureScope
@Subcomponent(
    modules = [
        UserActivityModule::class
    ]
)
interface UserActivityComponent {
    @Subcomponent.Factory
    interface Factory {
        fun create(): UserActivityComponent
    }
    fun inject(activity: UserActivity)
}