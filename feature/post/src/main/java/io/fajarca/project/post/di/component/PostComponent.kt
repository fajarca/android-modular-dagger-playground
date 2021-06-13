package io.fajarca.project.post.di.component

import dagger.Component
import io.fajarca.project.apiclient.di.ApiClientComponent
import io.fajarca.project.base.di.component.BaseComponent
import io.fajarca.project.base.di.scope.ModuleScope
import io.fajarca.project.post.di.module.NetworkModule
import io.fajarca.project.post.di.module.RepositoryModule
import io.fajarca.project.post.di.module.SubcomponentModule
import io.fajarca.project.post.di.module.ViewModelFactoryModule
import io.fajarca.project.post.presentation.detail.di.PostDetailComponent
import io.fajarca.project.post.presentation.list.di.PostActivityComponent

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
interface PostComponent {
    fun postActivityComponent() : PostActivityComponent.Factory
    fun postDetailComponent() : PostDetailComponent.Factory
}