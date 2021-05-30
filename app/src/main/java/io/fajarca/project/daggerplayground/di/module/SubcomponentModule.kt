package io.fajarca.project.daggerplayground.di.module

import dagger.Module
import io.fajarca.project.daggerplayground.login.di.LoginComponent

@Module(
    subcomponents = [
        LoginComponent::class
    ]
)
class SubcomponentModule