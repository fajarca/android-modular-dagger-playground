package io.fajarca.project.login.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.fajarca.project.base.ViewState
import io.fajarca.project.base.abstraction.Storage
import io.fajarca.project.base.abstraction.UseCase
import io.fajarca.project.base.abstraction.dispatcher.DispatcherProvider
import io.fajarca.project.base.di.scope.ModuleScope
import io.fajarca.project.base.extension.onError
import io.fajarca.project.base.extension.onSuccess
import io.fajarca.project.login.domain.entity.User
import io.fajarca.project.login.domain.usecase.GetUsersUseCase
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

@ModuleScope
class LoginViewModel @Inject constructor(
    private val storage: Storage,
    private val getUsersUseCase: GetUsersUseCase,
    private val dispatcherProvider: DispatcherProvider
) : ViewModel() {

    fun getPin(): String = runBlocking {
         storage.getString("pin")
    }

    fun setPin(pin: String) = runBlocking {
        storage.setString("pin", pin)
    }

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