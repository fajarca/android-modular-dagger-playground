package io.fajarca.project.post.presentation.list

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import io.fajarca.project.base.ViewState
import io.fajarca.project.base.abstraction.UseCase
import io.fajarca.project.base.abstraction.dispatcher.DispatcherProvider
import io.fajarca.project.base.extension.onError
import io.fajarca.project.base.extension.onSuccess
import io.fajarca.project.post.domain.entity.Post
import io.fajarca.project.post.domain.usecase.GetPostsUseCase
import javax.inject.Inject
import kotlinx.coroutines.launch


class PostViewModel @ViewModelInject constructor(
    private val getPostsUseCase: GetPostsUseCase,
    private val dispatcherProvider: DispatcherProvider
) : ViewModel() {

    private val _posts = MutableLiveData<ViewState<List<Post>>>()
    val post: LiveData<ViewState<List<Post>>>
        get() = _posts

    fun getPosts() {
        _posts.value = ViewState.Loading
        viewModelScope.launch(dispatcherProvider.io) {
            getPostsUseCase
                .execute(UseCase.NoParams)
                .onSuccess { users -> _posts.postValue(ViewState.Success(users)) }
                .onError { cause -> _posts.postValue(ViewState.Error(cause)) }
        }
    }
}