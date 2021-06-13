package io.fajarca.project.apiclient.di

import dagger.Component
import io.fajarca.project.apiclient.ApiClient


@Component(
    modules = [
        ApiModule::class
    ]
)

interface ApiClientComponent {

    @Component.Factory
    interface Factory {
        fun create(): ApiClientComponent
    }

    fun apiClient() : ApiClient
}
