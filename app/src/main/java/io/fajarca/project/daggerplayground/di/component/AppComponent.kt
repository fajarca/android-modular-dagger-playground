package io.fajarca.project.daggerplayground.di.component

import android.app.Application
import android.content.Context
import dagger.BindsInstance
import dagger.Component
import io.fajarca.project.base.di.component.BaseComponent
import io.fajarca.project.daggerplayground.di.module.CoroutineDispatcherModule
import io.fajarca.project.daggerplayground.di.module.DatabaseModule
import io.fajarca.project.daggerplayground.di.module.NetworkModule
import io.fajarca.project.daggerplayground.di.module.RouterModule
import io.fajarca.project.daggerplayground.di.module.StorageModule
import io.fajarca.project.daggerplayground.di.module.SubcomponentModule
import io.fajarca.project.daggerplayground.presentation.MainActivity
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        StorageModule::class,
        NetworkModule::class,
        SubcomponentModule::class,
        CoroutineDispatcherModule::class,
        RouterModule::class,
        DatabaseModule::class
    ]
)

interface AppComponent {

    @Component.Factory
    interface Factory {
        // With @BindsInstance, the Context passed in will be available in the graph
        fun create(@BindsInstance context: Context): AppComponent
    }

    fun inject(app: Application)
    fun inject(activity: MainActivity)
    fun baseComponent(): BaseComponent.Factory

}
