package io.fajarca.project.daggerplayground.presentation

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import io.fajarca.project.daggerplayground.R
import io.fajarca.project.daggerplayground.databinding.ActivityMainBinding
import io.fajarca.project.user.presentation.UserActivity

class MainActivity : AppCompatActivity() {

    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.button.setOnClickListener { UserActivity.start(this) }
    }

}