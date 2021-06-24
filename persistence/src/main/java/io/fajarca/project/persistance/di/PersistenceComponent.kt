package io.fajarca.project.persistance.di

import android.content.Context
import dagger.BindsInstance
import dagger.Component
import io.fajarca.project.persistance.DaggerPlaygroundDatabase

@Component(modules = [PersistenceModule::class])
interface PersistenceComponent {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context): PersistenceComponent
    }

    fun database() : DaggerPlaygroundDatabase
}