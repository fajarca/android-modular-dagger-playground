package io.fajarca.project.post.presentation.list

import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import io.fajarca.project.base.ViewState
import io.fajarca.project.base.abstraction.BaseActivity
import io.fajarca.project.base.abstraction.BaseApplication
import io.fajarca.project.base.extension.gone
import io.fajarca.project.base.extension.visible
import io.fajarca.project.base.network.exception.ClientErrorException
import io.fajarca.project.base.network.exception.NoInternetConnection
import io.fajarca.project.base.network.exception.ServerErrorException
import io.fajarca.project.base.router.Routable
import io.fajarca.project.base.router.Router
import io.fajarca.project.common.route.PostRouterData
import io.fajarca.project.post.databinding.ActivityPostBinding
import io.fajarca.project.post.di.component.DaggerPostComponent
import io.fajarca.project.post.presentation.detail.PostDetailActivity
import javax.inject.Inject

class PostActivity : BaseActivity<ActivityPostBinding>() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    @Inject
    lateinit var router: Router

    override fun getViewBinding(): (LayoutInflater) -> ActivityPostBinding {
        return ActivityPostBinding::inflate
    }

    private val adapter by lazy { PostRecyclerAdapter() }
    private val viewModel by viewModels<PostViewModel> { viewModelFactory }

    override fun onCreate(savedInstanceState: Bundle?) {
        val postComponent = DaggerPostComponent
            .builder()
            .baseComponent((application as BaseApplication).getBaseComponent())
            .build()

        postComponent.postActivityComponent().create().inject(this)

        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        handleIntentArguments()
        setupRecyclerView()
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