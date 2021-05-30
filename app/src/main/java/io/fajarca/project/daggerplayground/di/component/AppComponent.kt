package io.fajarca.project.daggerplayground.di.component

import io.fajarca.project.daggerplayground.di.module.ActivityBuilder
import io.fajarca.project.daggerplayground.di.module.AppModule
import io.fajarca.project.daggerplayground.di.module.NetworkModule
import io.fajarca.project.daggerplayground.di.module.ViewModelFactoryModule
import io.fajarca.project.daggerplayground.di.module.ViewModelModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import io.fajarca.project.daggerplayground.DaggerPlaygroundApp
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidInjectionModule::class,
        AppModule::class,
        NetworkModule::class,
        ActivityBuilder::class,
        ViewModelModule::class,
        ViewModelFactoryModule::class,
    ]
)

interface AppComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: DaggerPlaygroundApp): Builder

        fun build(): AppComponent
    }

    fun inject(app: DaggerPlaygroundApp)
}
