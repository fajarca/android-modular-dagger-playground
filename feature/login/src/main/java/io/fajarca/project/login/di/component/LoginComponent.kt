package io.fajarca.project.login.di.component

import dagger.Component
import io.fajarca.project.base.di.scope.ModuleScope
import io.fajarca.project.base.di.component.BaseComponent
import io.fajarca.project.login.di.module.NetworkModule
import io.fajarca.project.login.di.module.RepositoryModule
import io.fajarca.project.login.di.module.ViewModelFactoryModule
import io.fajarca.project.login.di.module.ViewModelModule
import io.fajarca.project.login.presentation.LoginActivity

@ModuleScope
@Component(
    dependencies = [BaseComponent::class],
    modules = [NetworkModule::class, ViewModelFactoryModule::class, ViewModelModule::class, RepositoryModule::class]
)
interface LoginComponent {
    fun inject(activity: LoginActivity)
}