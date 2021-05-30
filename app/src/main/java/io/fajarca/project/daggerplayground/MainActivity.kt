package io.fajarca.project.daggerplayground

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import dagger.android.AndroidInjection
import io.fajarca.project.daggerplayground.di.factory.ViewModelFactory
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)

        val viewModel = ViewModelProvider(this, viewModelFactory)[MainViewModel::class.java]

        val pin = viewModel.getPin()
    }

}