package io.fajarca.project.user.di.module

import dagger.Module
import io.fajarca.project.user.presentation.detail.di.UserDetailComponent
import io.fajarca.project.user.presentation.main.di.UserActivityComponent

@Module(
    subcomponents = [
        UserActivityComponent::class,
        UserDetailComponent::class
    ]
)
class SubcomponentModule