package io.fajarca.project.post.di.module

import dagger.Module
import io.fajarca.project.post.presentation.detail.di.PostDetailComponent
import io.fajarca.project.post.presentation.list.di.PostActivityComponent

@Module(
    subcomponents = [
        PostActivityComponent::class,
        PostDetailComponent::class
    ]
)
class SubcomponentModule