package io.fajarca.project.daggerplayground.di.component

import io.fajarca.project.daggerplayground.di.module.ActivityBuilder
import io.fajarca.project.daggerplayground.di.module.ApiServiceModule
import io.fajarca.project.daggerplayground.di.module.AppModule
import io.fajarca.project.daggerplayground.di.module.CoroutineDispatcherModule
import io.fajarca.project.daggerplayground.di.module.FragmentModule
import io.fajarca.project.daggerplayground.di.module.NetworkModule
import io.fajarca.project.daggerplayground.di.module.RepositoryModule
import io.fajarca.project.daggerplayground.di.module.ServiceBuilderModule
import io.fajarca.project.daggerplayground.di.module.ViewModelFactoryModule
import io.fajarca.project.daggerplayground.di.module.ViewModelModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import io.fajarca.project.daggerplayground.MyPertaminaApp
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class,
    NetworkModule::class,
    RepositoryModule::class,
    AndroidInjectionModule::class,
    ActivityBuilder::class,
    FragmentModule::class,
    ViewModelModule::class,
    ViewModelFactoryModule::class,
    ApiServiceModule::class,
    CoroutineDispatcherModule::class,
    ServiceBuilderModule::class
])

interface AppComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: MyPertaminaApp): Builder

        fun build(): AppComponent
    }

    fun inject(app: MyPertaminaApp)
}
