package io.fajarca.project.post.presentation.list

import android.os.Bundle
import android.view.LayoutInflater
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import io.fajarca.project.analytics.Analytic
import io.fajarca.project.apiclient.exception.ClientErrorException
import io.fajarca.project.apiclient.exception.NoInternetConnection
import io.fajarca.project.apiclient.exception.ServerErrorException
import io.fajarca.project.core.ViewState
import io.fajarca.project.core.abstraction.activity.BaseActivity
import io.fajarca.project.core.extension.gone
import io.fajarca.project.core.extension.visible
import io.fajarca.project.core.router.Routable
import io.fajarca.project.core.router.Router
import io.fajarca.project.navigation.PostRouterData
import io.fajarca.project.post.databinding.ActivityPostBinding
import io.fajarca.project.post.presentation.detail.PostDetailActivity
import javax.inject.Inject
import kotlinx.coroutines.launch

@AndroidEntryPoint
class PostActivity : BaseActivity<ActivityPostBinding>() {

    @Inject
    lateinit var analytic: Analytic

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
            lifecycleScope.launch {
                val result = analytic.logEvent("Post Detail", post.title)
            }
        }
    }


    private fun observePosts() {
        viewModel.post.observe(this) {
            when (it) {
                ViewState.Loading -> {
                    binding.recyclerView.gone()
                    binding.progressBar.visible()
                }
                is ViewState.Success -> {
                    binding.recyclerView.visible()
                    binding.progressBar.gone()
                    adapter.submitList(it.data)
                }
                is ViewState.Error -> {
                    binding.recyclerView.gone()
                    binding.progressBar.gone()
                    when (val cause = it.cause) {
                        is ClientErrorException -> {
                            val code = cause.code
                            Snackbar.make(binding.root, "Client Error", Snackbar.LENGTH_LONG).show()
                        }
                        is ServerErrorException -> {
                            val code = cause.code
                            Snackbar.make(binding.root, "Server Error", Snackbar.LENGTH_LONG).show()
                        }
                        is NoInternetConnection -> {
                            Snackbar.make(binding.root, "No connection", Snackbar.LENGTH_LONG).show()
                        }
                    }

                }
            }
        }
    }


}