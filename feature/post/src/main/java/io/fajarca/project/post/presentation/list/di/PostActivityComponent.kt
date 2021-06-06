package io.fajarca.project.post.presentation.list.di

import dagger.Subcomponent
import io.fajarca.project.base.di.scope.FeatureScope
import io.fajarca.project.post.presentation.list.PostActivity

@FeatureScope
@Subcomponent(
    modules = [
        PostActivityModule::class
    ]
)
interface PostActivityComponent {
    @Subcomponent.Factory
    interface Factory {
        fun create(): PostActivityComponent
    }
    fun inject(activity: PostActivity)
}