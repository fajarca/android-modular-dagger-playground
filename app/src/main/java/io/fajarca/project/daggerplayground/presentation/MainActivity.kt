package io.fajarca.project.daggerplayground.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import io.fajarca.project.base.router.Router
import io.fajarca.project.daggerplayground.DaggerPlaygroundApp
import io.fajarca.project.daggerplayground.databinding.ActivityMainBinding
import io.fajarca.project.post.PostRouterData
import io.fajarca.project.user.presentation.UserRouterData
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    @Inject
    lateinit var router : Router

    override fun onCreate(savedInstanceState: Bundle?) {
        (application as DaggerPlaygroundApp).appComponent.inject(this)

        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setupView()
    }

    private fun setupView() {
        binding.btnGoToUserModule.setOnClickListener {
            router.routeToActivity(this, UserRouterData(4))
        }
        binding.btnGoToPostModule.setOnClickListener {
            router.routeToActivity(this, PostRouterData(4))
        }
    }

}