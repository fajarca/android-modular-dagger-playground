package io.fajarca.project.daggerplayground

import android.app.Application
import io.fajarca.project.base.di.component.BaseComponent
import io.fajarca.project.base.di.component.BaseComponentProvider
import io.fajarca.project.daggerplayground.di.component.AppComponent
import io.fajarca.project.daggerplayground.di.component.DaggerAppComponent


class DaggerPlaygroundApp : Application(), BaseComponentProvider {


    // Instance of the AppComponent that will be used by all the Activities in the project
    private val appComponent: AppComponent by lazy {
        // Creates an instance of AppComponent using its Factory constructor
        // We pass the applicationContext that will be used as Context in the graph
        DaggerAppComponent.factory().create(applicationContext)
    }

    override fun onCreate() {
        super.onCreate()
        appComponent.inject(this)
    }

    override fun provideBaseComponent(): BaseComponent.Factory {
        return appComponent.baseComponent()
    }


}
