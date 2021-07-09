package io.fajarca.project.post.presentation.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import io.fajarca.project.core.ViewState
import io.fajarca.project.core.dispatcher.CoroutineDispatcherProvider
import io.fajarca.project.core.extension.onError
import io.fajarca.project.core.extension.onSuccess
import io.fajarca.project.post.domain.entity.Post
import io.fajarca.project.post.domain.usecase.GetPostDetailUseCase
import javax.inject.Inject
import kotlinx.coroutines.launch


@HiltViewModel
class PostDetailViewModel @Inject constructor(
    private val getPostDetailUseCase: GetPostDetailUseCase,
    private val coroutineDispatcherProvider: CoroutineDispatcherProvider
) : ViewModel() {

    private val _post = MutableLiveData<ViewState<Post>>()
    val post: LiveData<ViewState<Post>>
        get() = _post

    fun getPostDetail(userId : Int) {
        _post.value = ViewState.Loading
        viewModelScope.launch(coroutineDispatcherProvider.io) {
            getPostDetailUseCase
                .execute(userId)
                .onSuccess { users -> _post.postValue(ViewState.Success(users)) }
                .onError { cause -> _post.postValue(ViewState.Error(cause)) }
        }
    }
}