package io.fajarca.project.post.presentation.detail.di

import dagger.Subcomponent
import io.fajarca.project.base.di.scope.FeatureScope
import io.fajarca.project.post.presentation.detail.PostDetailActivity

@FeatureScope
@Subcomponent(
    modules = [
        PostDetailModule::class
    ]
)
interface PostDetailComponent {
    @Subcomponent.Factory
    interface Factory {
        fun create(): PostDetailComponent
    }
    fun inject(activity: PostDetailActivity)
}