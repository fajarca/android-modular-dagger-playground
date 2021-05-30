package io.fajarca.project.daggerplayground

import android.app.Application
import io.fajarca.project.daggerplayground.di.component.AppComponent
import io.fajarca.project.daggerplayground.di.component.DaggerAppComponent


class DaggerPlaygroundApp : Application() {


    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        initAppDependencyInjection()

    }
    private fun initAppDependencyInjection() {
        appComponent = DaggerAppComponent.builder()
            .application(this)
            .build()
        appComponent.inject(this)
    }


}
