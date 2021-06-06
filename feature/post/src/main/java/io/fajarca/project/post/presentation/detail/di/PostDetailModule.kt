package io.fajarca.project.post.presentation.detail.di

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import io.fajarca.project.base.di.annotation.ViewModelKey
import io.fajarca.project.post.presentation.detail.PostDetailViewModel

@Module
abstract class PostDetailModule {

    @Binds
    @IntoMap
    @ViewModelKey(PostDetailViewModel::class)
    internal abstract fun providesPostDetailViewModel(viewModel: PostDetailViewModel): ViewModel

}
