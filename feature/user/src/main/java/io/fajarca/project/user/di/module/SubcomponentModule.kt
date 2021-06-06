package io.fajarca.project.user.di.module

import dagger.Module
import io.fajarca.project.user.presentation.detail.di.UserDetailComponent

@Module(
    subcomponents = [
        UserDetailComponent::class
    ]
)
class SubcomponentModule