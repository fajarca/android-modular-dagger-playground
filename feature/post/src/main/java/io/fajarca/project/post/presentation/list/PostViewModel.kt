package io.fajarca.project.post.presentation.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import io.fajarca.project.core.ViewState
import io.fajarca.project.core.abstraction.usecase.UseCase
import io.fajarca.project.core.dispatcher.CoroutineDispatcherProvider
import io.fajarca.project.core.extension.onError
import io.fajarca.project.core.extension.onSuccess
import io.fajarca.project.post.domain.entity.Post
import io.fajarca.project.post.domain.usecase.GetPostsUseCase
import javax.inject.Inject
import kotlinx.coroutines.launch

@HiltViewModel
class PostViewModel @Inject constructor(
    private val getPostsUseCase: GetPostsUseCase,
    private val coroutineDispatcherProvider: CoroutineDispatcherProvider
) : ViewModel() {

    private val _posts = MutableLiveData<ViewState<List<Post>>>()
    val post: LiveData<ViewState<List<Post>>>
        get() = _posts

    fun getPosts() {
        _posts.value = ViewState.Loading
        viewModelScope.launch(coroutineDispatcherProvider.io) {
            getPostsUseCase
                .execute(UseCase.NoParams)
                .onSuccess { users -> _posts.postValue(ViewState.Success(users)) }
                .onError { cause -> _posts.postValue(ViewState.Error(cause)) }
        }
    }
}