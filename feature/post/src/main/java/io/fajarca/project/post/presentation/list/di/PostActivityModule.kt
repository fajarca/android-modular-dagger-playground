package io.fajarca.project.post.presentation.list.di

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import io.fajarca.project.base.di.annotation.ViewModelKey
import io.fajarca.project.post.presentation.list.PostViewModel


@Module
abstract class PostActivityModule {

    @Binds
    @IntoMap
    @ViewModelKey(PostViewModel::class)
    internal abstract fun providesPostViewModel(viewModel: PostViewModel): ViewModel

}
