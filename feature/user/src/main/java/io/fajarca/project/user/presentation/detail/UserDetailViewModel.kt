package io.fajarca.project.user.presentation.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import io.fajarca.project.core.ViewState
import io.fajarca.project.core.dispatcher.CoroutineDispatcherProvider
import io.fajarca.project.core.extension.onError
import io.fajarca.project.core.extension.onSuccess
import io.fajarca.project.user.domain.entity.User
import io.fajarca.project.user.domain.usecase.GetUserDetailUseCase
import javax.inject.Inject
import kotlinx.coroutines.launch

@HiltViewModel
class UserDetailViewModel @Inject constructor(
    private val getUserDetailUseCase: GetUserDetailUseCase,
    private val coroutineDispatcherProvider: CoroutineDispatcherProvider
) : ViewModel() {

    private val _user = MutableLiveData<ViewState<User>>()
    val user: LiveData<ViewState<User>>
        get() = _user

    fun getUserDetail(userId : Int) {
        _user.value = ViewState.Loading
        viewModelScope.launch(coroutineDispatcherProvider.io) {
            getUserDetailUseCase
                .execute(userId)
                .onSuccess { users -> _user.postValue(ViewState.Success(users)) }
                .onError { cause -> _user.postValue(ViewState.Error(cause)) }
        }
    }
}