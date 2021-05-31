package io.fajarca.project.base.di.component

interface BaseComponentProvider {
    fun provideBaseComponent() : BaseComponent.Factory
}