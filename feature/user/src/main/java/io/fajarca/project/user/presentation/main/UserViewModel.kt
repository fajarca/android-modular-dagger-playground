package io.fajarca.project.user.presentation.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.fajarca.project.base.ViewState
import io.fajarca.project.base.abstraction.UseCase
import io.fajarca.project.base.abstraction.dispatcher.DispatcherProvider
import io.fajarca.project.base.di.scope.ModuleScope
import io.fajarca.project.base.extension.onError
import io.fajarca.project.base.extension.onSuccess
import io.fajarca.project.user.domain.entity.User
import io.fajarca.project.user.domain.usecase.GetUsersUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

@ModuleScope
class UserViewModel @Inject constructor(
    private val getUsersUseCase: GetUsersUseCase,
    private val dispatcherProvider: DispatcherProvider
) : ViewModel() {

    private val _users = MutableLiveData<ViewState<List<User>>>()
    val users: LiveData<ViewState<List<User>>>
        get() = _users

    fun getUsers() {
        _users.value = ViewState.Loading
        viewModelScope.launch(dispatcherProvider.io) {
            getUsersUseCase
                .execute(UseCase.NoParams)
                .onSuccess { users -> _users.postValue(ViewState.Success(users)) }
                .onError { cause -> _users.postValue(ViewState.Error(cause)) }
        }
    }
}