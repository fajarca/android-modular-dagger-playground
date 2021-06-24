package io.fajarca.project.post.presentation.list

import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import io.fajarca.project.base.ViewState
import io.fajarca.project.base.abstraction.BaseActivity
import io.fajarca.project.base.abstraction.BaseApplication
import io.fajarca.project.base.extension.gone
import io.fajarca.project.base.extension.visible
import io.fajarca.project.apiclient.exception.ClientErrorException
import io.fajarca.project.apiclient.exception.NoInternetConnection
import io.fajarca.project.apiclient.exception.ServerErrorException
import io.fajarca.project.base.router.Routable
import io.fajarca.project.base.router.Router
import io.fajarca.project.common.navigation.PostRouterData
import io.fajarca.project.post.databinding.ActivityPostBinding
import io.fajarca.project.post.di.component.DaggerPostComponent
import io.fajarca.project.post.presentation.detail.PostDetailActivity
import javax.inject.Inject

class PostActivity : BaseActivity<ActivityPostBinding, PostViewModel>() {

    @Inject
    lateinit var router: Router

    override val getViewBinding: (LayoutInflater) -> ActivityPostBinding
        get() = ActivityPostBinding::inflate

    override val getViewModelClass: Class<PostViewModel>
        get() = PostViewModel::class.java

    private val adapter by lazy { PostRecyclerAdapter() }

    override fun setupDaggerComponent() {
        val postComponent = DaggerPostComponent
            .builder()
            .baseComponent((application as BaseApplication).getBaseComponent())
            .build()

        postComponent.postActivityComponent().create().inject(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        handleIntentArguments()
        setupRecyclerView()
        setupToolbar()
        observePosts()
        viewModel.getPosts()
    }

    private fun setupToolbar() {
        val toolbar = binding.toolbar.toolbar
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
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