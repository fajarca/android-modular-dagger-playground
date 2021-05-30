package io.fajarca.project.daggerplayground.di.component

import android.app.Application
import io.fajarca.project.daggerplayground.di.module.NetworkModule
import io.fajarca.project.daggerplayground.di.module.ViewModelFactoryModule
import dagger.BindsInstance
import dagger.Component
import io.fajarca.project.daggerplayground.DaggerPlaygroundApp
import io.fajarca.project.daggerplayground.di.module.AppModule
import io.fajarca.project.daggerplayground.di.module.StorageModule
import io.fajarca.project.daggerplayground.di.module.ViewModelModule
import io.fajarca.project.daggerplayground.login.LoginActivity
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AppModule::class,
        NetworkModule::class,
        ViewModelFactoryModule::class,
        ViewModelModule::class,
        StorageModule::class
    ]
)

interface AppComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }

    fun inject(app: DaggerPlaygroundApp)
    fun inject(activity : LoginActivity)
}
