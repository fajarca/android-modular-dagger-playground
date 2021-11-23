package io.fajarca.project.core.abstraction.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding

abstract class BaseFragment<out V : ViewBinding> : Fragment() {
    private var _binding: V? = null
    protected val binding: V
        get() = requireNotNull(_binding)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        if (_binding == null) {
            _binding = getViewBinding(inflater, container, false)
        }
        return requireNotNull(_binding).root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    abstract val getViewBinding: (LayoutInflater, ViewGroup?, Boolean) -> V
}