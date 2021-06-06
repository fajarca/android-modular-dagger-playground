package io.fajarca.project.user.presentation.main.di

import dagger.Subcomponent
import io.fajarca.project.base.di.scope.FeatureScope
import io.fajarca.project.user.presentation.detail.UserDetailActivity
import io.fajarca.project.user.presentation.main.UserActivity

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