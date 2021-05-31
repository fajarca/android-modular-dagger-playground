package io.fajarca.project.login.di.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import io.fajarca.project.base.ModuleScope
import javax.inject.Inject
import javax.inject.Provider

@ModuleScope
class ViewModelFactory @Inject constructor(private val viewModels: MutableMap<Class<out ViewModel>, Provider<ViewModel>>) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T = viewModels[modelClass]?.get() as T
}