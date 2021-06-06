package io.fajarca.project.post.presentation.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.fajarca.project.base.ViewState
import io.fajarca.project.base.abstraction.dispatcher.DispatcherProvider
import io.fajarca.project.base.di.scope.FeatureScope
import io.fajarca.project.base.extension.onError
import io.fajarca.project.base.extension.onSuccess
import io.fajarca.project.post.domain.entity.Post
import io.fajarca.project.post.domain.usecase.GetPostDetailUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

@FeatureScope
class PostDetailViewModel @Inject constructor(
    private val getPostDetailUseCase: GetPostDetailUseCase,
    private val dispatcherProvider: DispatcherProvider
) : ViewModel() {

    private val _post = MutableLiveData<ViewState<Post>>()
    val post: LiveData<ViewState<Post>>
        get() = _post

    fun getPostDetail(userId : Int) {
        _post.value = ViewState.Loading
        viewModelScope.launch(dispatcherProvider.io) {
            getPostDetailUseCase
                .execute(userId)
                .onSuccess { users -> _post.postValue(ViewState.Success(users)) }
                .onError { cause -> _post.postValue(ViewState.Error(cause)) }
        }
    }
}