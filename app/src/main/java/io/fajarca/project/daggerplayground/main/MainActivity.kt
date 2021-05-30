package io.fajarca.project.daggerplayground.main

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.button.MaterialButton
import dagger.android.AndroidInjection
import io.fajarca.project.daggerplayground.R
import io.fajarca.project.daggerplayground.di.factory.ViewModelFactory
import io.fajarca.project.daggerplayground.login.di.LoginActivity
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val viewModel = ViewModelProvider(this, viewModelFactory)[MainViewModel::class.java]
        viewModel.print()

        val button = findViewById<MaterialButton>(R.id.btnLogin)
        button.setOnClickListener {
            LoginActivity.start(this)
        }
    }
}