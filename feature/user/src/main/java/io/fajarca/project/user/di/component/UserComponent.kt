package io.fajarca.project.user.di.component

import dagger.Component
import io.fajarca.project.base.di.component.BaseComponent
import io.fajarca.project.base.di.scope.ModuleScope
import io.fajarca.project.user.di.module.NetworkModule
import io.fajarca.project.user.di.module.RepositoryModule
import io.fajarca.project.user.di.module.SubcomponentModule
import io.fajarca.project.user.di.module.ViewModelFactoryModule
import io.fajarca.project.user.di.module.ViewModelModule
import io.fajarca.project.user.presentation.detail.di.UserDetailComponent
import io.fajarca.project.user.presentation.main.UserActivity

@ModuleScope
@Component(
    dependencies = [BaseComponent::class],
    modules = [
        NetworkModule::class,
        ViewModelFactoryModule::class,
        ViewModelModule::class,
        RepositoryModule::class,
        SubcomponentModule::class
    ]
)
interface UserComponent {
    fun inject(activity: UserActivity)
    fun userDetailComponent() : UserDetailComponent.Factory
}