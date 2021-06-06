package io.fajarca.project.user.presentation.detail.di

import dagger.Subcomponent
import io.fajarca.project.base.di.scope.FeatureScope
import io.fajarca.project.user.presentation.detail.UserDetailActivity

@FeatureScope
@Subcomponent(
    modules = [
        UserDetailModule::class
    ]
)
interface UserDetailComponent {
    @Subcomponent.Factory
    interface Factory {
        fun create(): UserDetailComponent
    }
    fun inject(activity: UserDetailActivity)
}