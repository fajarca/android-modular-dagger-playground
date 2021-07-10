package io.fajarca.project.user.presentation.list

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Toast
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import io.fajarca.project.core.ViewState
import io.fajarca.project.core.abstraction.activity.BaseActivity
import io.fajarca.project.core.extension.gone
import io.fajarca.project.core.extension.visible
import io.fajarca.project.apiclient.exception.ClientErrorException
import io.fajarca.project.apiclient.exception.NoInternetConnection
import io.fajarca.project.apiclient.exception.ServerErrorException
import io.fajarca.project.core.router.Routable
import io.fajarca.project.core.router.Router
import io.fajarca.project.navigation.UserRouterData
import io.fajarca.project.user.databinding.ActivityUserBinding
import io.fajarca.project.user.presentation.detail.UserDetailActivity
import javax.inject.Inject

@AndroidEntryPoint
class UserActivity : BaseActivity<ActivityUserBinding>() {

    @Inject
    lateinit var router: Router

    private val adapter by lazy { UserRecyclerAdapter() }

    override val getViewBinding: (LayoutInflater) -> ActivityUserBinding
        get() = ActivityUserBinding::inflate

    private val viewModel: UserViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        handleIntentArguments()
        setupRecyclerView()
        setupView()
        setupToolbar()
        observeUsers()
        viewModel.getUsers()
    }

    private fun handleIntentArguments() {
        val extras = intent.extras
        val data = extras?.getParcelable(Routable.ROUTE_DATA) as? UserRouterData
        val userId = data?.userId
    }

    private fun setupView() {
        binding.btnGoToPostModule.setOnClickListener {
            router.routeToLink(this, 1, Uri.parse("app://links/post?id=100"))
            //router.routeToActivity(this, 1, PostRouterData(1))
            //router.routeToActivity(this, PostRouterData(2))
        }
    }

    private fun setupRecyclerView() {
        val layoutManager = LinearLayoutManager(this)
        binding.recyclerView.layoutManager = layoutManager
        binding.recyclerView.adapter = adapter
        adapter.setOnUserSelected { user ->
            UserDetailActivity.start(this, user.id)
        }
    }


    private fun observeUsers() {
        viewModel.users.observe(this) {
            when (it) {
                ViewState.Loading -> {
                    binding.progressBar.visible()
                }
                is ViewState.Success -> {
                    binding.progressBar.gone()
                    adapter.submitList(it.data)
                }
                is ViewState.Error -> {
                    binding.progressBar.gone()
                    when (val cause = it.cause) {
                        is ClientErrorException -> {
                            val code = cause.code
                        }
                        is ServerErrorException -> {
                            val code = cause.code
                        }
                        is NoInternetConnection -> {
                            Toast.makeText(this, "No connection", Toast.LENGTH_LONG).show()
                        }
                    }

                }
            }
        }
    }

}