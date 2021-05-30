package io.fajarca.project.daggerplayground.login.di

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import dagger.android.AndroidInjection
import io.fajarca.project.daggerplayground.R
import io.fajarca.project.daggerplayground.di.factory.ViewModelFactory
import javax.inject.Inject

class LoginActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)


        val viewModel = ViewModelProvider(this, viewModelFactory)[LoginViewModel::class.java]
        val pin = viewModel.getPin()
    }


}