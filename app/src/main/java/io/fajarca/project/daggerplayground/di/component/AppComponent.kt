package io.fajarca.project.daggerplayground.di.component

import android.app.Application
import io.fajarca.project.daggerplayground.di.module.ActivityBuilderModule
import io.fajarca.project.daggerplayground.di.module.AppModule
import io.fajarca.project.daggerplayground.di.module.NetworkModule
import io.fajarca.project.daggerplayground.di.module.ViewModelFactoryModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import io.fajarca.project.daggerplayground.DaggerPlaygroundApp
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        AppModule::class,
        NetworkModule::class,
        ActivityBuilderModule::class,
        ViewModelFactoryModule::class,
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
}
