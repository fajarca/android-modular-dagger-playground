package io.fajarca.project.post.presentation.list

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
import io.fajarca.project.common.navigation.PostRouterData
import io.fajarca.project.post.databinding.ActivityPostBinding
import io.fajarca.project.post.presentation.detail.PostDetailActivity
import javax.inject.Inject

@AndroidEntryPoint
class PostActivity : BaseActivity<ActivityPostBinding>() {

    @Inject
    lateinit var router: Router

    override val getViewBinding: (LayoutInflater) -> ActivityPostBinding
        get() = ActivityPostBinding::inflate

    private val viewModel: PostViewModel by viewModels()

    private val adapter by lazy { PostRecyclerAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        handleIntentArguments()
        setupRecyclerView()
        setupToolbar()
        observePosts()
        viewModel.getPosts()
    }

    private fun handleIntentArguments() {
        val extras = intent.extras
        val data = extras?.getParcelable(Routable.ROUTE_DATA) as? PostRouterData
        val postId = data?.postId
    }
    

    private fun setupRecyclerView() {
        val layoutManager = LinearLayoutManager(this)
        binding.recyclerView.layoutManager = layoutManager
        binding.recyclerView.adapter = adapter
        adapter.setOnPostSelected { post ->
            PostDetailActivity.start(this, post.id)
        }
    }


    private fun observePosts() {
        viewModel.post.observe(this) {
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