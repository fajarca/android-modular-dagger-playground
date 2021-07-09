package io.fajarca.project.core.abstraction.activity

import android.os.Bundle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.viewbinding.ViewBinding
import javax.inject.Inject

abstract class DynamicFeatureActivity<V : ViewBinding, VM : ViewModel> : BaseActivity<V>() {

    private lateinit var _viewModel: VM
    protected val viewModel: VM
        get() = _viewModel

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    override fun onCreate(savedInstanceState: Bundle?) {
        setupDaggerComponent()
        super.onCreate(savedInstanceState)
        _viewModel = ViewModelProvider(this, viewModelFactory).get(getViewModelClass)
    }

    abstract fun setupDaggerComponent()

    abstract val getViewModelClass: Class<VM>
}