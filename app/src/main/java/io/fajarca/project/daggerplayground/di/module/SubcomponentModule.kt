package io.fajarca.project.daggerplayground.di.module

import dagger.Module
import io.fajarca.project.base.di.component.BaseComponent

@Module(
    subcomponents = [
        BaseComponent::class
    ]
)
class SubcomponentModule