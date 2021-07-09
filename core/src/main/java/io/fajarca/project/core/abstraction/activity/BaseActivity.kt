package io.fajarca.project.core.abstraction.activity

import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.viewbinding.ViewBinding
import io.fajarca.project.core.R

abstract class BaseActivity<V : ViewBinding> : AppCompatActivity() {

    private var _binding: V? = null
    protected val binding: V
        get() = requireNotNull(_binding)

    private var _toolbar: Toolbar? = null
    protected val toolbar: Toolbar?
        get() = _toolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (_binding == null) {
            _binding = getViewBinding(layoutInflater)
        }

        setContentView(_binding?.root)
        setupToolbar()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    abstract val getViewBinding: (LayoutInflater) -> V

    open fun setupToolbar() {
        _toolbar = findViewById(R.id.toolbar)
        if (_toolbar != null) {
            setSupportActionBar(_toolbar)
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
        }
    }

}