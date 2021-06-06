package io.fajarca.project.user.presentation.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.fajarca.project.base.ViewState
import io.fajarca.project.base.abstraction.dispatcher.DispatcherProvider
import io.fajarca.project.base.di.scope.FeatureScope
import io.fajarca.project.base.extension.onError
import io.fajarca.project.base.extension.onSuccess
import io.fajarca.project.user.domain.entity.User
import io.fajarca.project.user.domain.usecase.GetUserDetailUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

@FeatureScope
class UserDetailViewModel @Inject constructor(
    private val getUserDetailUseCase: GetUserDetailUseCase,
    private val dispatcherProvider: DispatcherProvider
) : ViewModel() {

    private val _user = MutableLiveData<ViewState<User>>()
    val user: LiveData<ViewState<User>>
        get() = _user

    fun getUserDetail(userId : Int) {
        _user.value = ViewState.Loading
        viewModelScope.launch(dispatcherProvider.io) {
            getUserDetailUseCase
                .execute(userId)
                .onSuccess { users -> _user.postValue(ViewState.Success(users)) }
                .onError { cause -> _user.postValue(ViewState.Error(cause)) }
        }
    }
}