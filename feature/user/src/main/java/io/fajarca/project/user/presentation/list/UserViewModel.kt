package io.fajarca.project.user.presentation.list

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
import io.fajarca.project.user.domain.entity.User
import io.fajarca.project.user.domain.usecase.GetUsersUseCase
import javax.inject.Inject
import kotlinx.coroutines.launch

@HiltViewModel
class UserViewModel @Inject constructor(
    private val getUsersUseCase: GetUsersUseCase,
    private val coroutineDispatcherProvider: CoroutineDispatcherProvider
) : ViewModel() {

    private val _users = MutableLiveData<ViewState<List<User>>>()
    val users: LiveData<ViewState<List<User>>>
        get() = _users

    fun getUsers() {
        _users.value = ViewState.Loading
        viewModelScope.launch(coroutineDispatcherProvider.io) {
            getUsersUseCase
                .execute(UseCase.NoParams)
                .onSuccess { users -> _users.postValue(ViewState.Success(users)) }
                .onError { cause -> _users.postValue(ViewState.Error(cause)) }
        }
    }
}