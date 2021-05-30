package io.fajarca.project.daggerplayground.di.component

import android.content.Context
import dagger.BindsInstance
import dagger.Component
import io.fajarca.project.daggerplayground.di.module.ApiServiceModule
import io.fajarca.project.daggerplayground.di.module.NetworkModule
import io.fajarca.project.daggerplayground.di.module.StorageModule
import io.fajarca.project.daggerplayground.di.module.SubcomponentModule
import io.fajarca.project.daggerplayground.di.module.ViewModelFactoryModule
import io.fajarca.project.daggerplayground.di.module.ViewModelModule
import io.fajarca.project.daggerplayground.login.di.LoginComponent
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        ViewModelFactoryModule::class,
        ViewModelModule::class,
        StorageModule::class,
        NetworkModule::class,
        ApiServiceModule::class,
        SubcomponentModule::class
    ]
)

interface AppComponent {

    @Component.Factory
    interface Factory {
        // With @BindsInstance, the Context passed in will be available in the graph
        fun create(@BindsInstance context: Context): AppComponent
    }

    fun loginComponent() : LoginComponent.Factory
}
