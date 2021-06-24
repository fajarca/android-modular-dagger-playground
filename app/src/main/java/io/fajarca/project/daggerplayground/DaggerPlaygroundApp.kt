package io.fajarca.project.daggerplayground

import android.content.Context
import com.google.android.play.core.splitcompat.SplitCompat
import io.fajarca.project.apiclient.di.ApiClientComponent
import io.fajarca.project.apiclient.di.DaggerApiClientComponent
import io.fajarca.project.base.abstraction.BaseApplication
import io.fajarca.project.base.di.component.BaseComponent
import io.fajarca.project.daggerplayground.di.component.AppComponent
import io.fajarca.project.daggerplayground.di.component.DaggerAppComponent
import io.fajarca.project.persistance.di.DaggerPersistenceComponent
import io.fajarca.project.persistance.di.PersistenceComponent


class DaggerPlaygroundApp : BaseApplication() {


    private val apiClientComponent: ApiClientComponent by lazy {
        DaggerApiClientComponent.factory().create()
    }

    private val persistenceComponent: PersistenceComponent by lazy {
        DaggerPersistenceComponent.factory().create(applicationContext)
    }

    // Instance of the AppComponent that will be used by all the Activities in the project
    val appComponent: AppComponent by lazy {
        // Creates an instance of AppComponent using its Factory constructor
        // We pass the applicationContext that will be used as Context in the graph
        DaggerAppComponent.factory()
            .create(
                applicationContext,
                apiClientComponent,
                persistenceComponent
            )
    }

    override fun getBaseComponent(): BaseComponent {
        return appComponent.baseComponent().create()
    }

    override fun onCreate() {
        super.onCreate()
        appComponent.inject(this)
    }


    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        SplitCompat.install(this)
    }

}
