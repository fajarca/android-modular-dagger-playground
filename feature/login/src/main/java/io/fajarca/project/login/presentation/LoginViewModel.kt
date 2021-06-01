package io.fajarca.project.login.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.fajarca.project.base.di.scope.ModuleScope
import io.fajarca.project.base.abstraction.Storage
import io.fajarca.project.login.domain.entity.User
import io.fajarca.project.login.domain.usecase.GetUsersUseCase
import javax.inject.Inject
import io.fajarca.project.base.Result
import io.fajarca.project.base.abstraction.UseCase
import io.fajarca.project.base.abstraction.dispatcher.DispatcherProvider
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

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

    private val _users = MutableLiveData<Result<List<User>>>()
    val users: LiveData<Result<List<User>>>
        get() = _users

    fun getUsers() {
        _users.value = Result.Loading
        viewModelScope.launch(dispatcherProvider.io) {
            _users.postValue(getUsersUseCase.execute(UseCase.None))
        }
    }
}