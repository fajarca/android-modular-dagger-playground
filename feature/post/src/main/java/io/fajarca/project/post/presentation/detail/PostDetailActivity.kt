package io.fajarca.project.post.presentation.detail

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.ViewModelProvider
import io.fajarca.project.apiclient.di.DaggerApiClientComponent
import io.fajarca.project.base.ViewState
import io.fajarca.project.base.abstraction.BaseActivity
import io.fajarca.project.base.abstraction.BaseApplication
import io.fajarca.project.base.extension.gone
import io.fajarca.project.base.extension.visible
import io.fajarca.project.base.network.exception.ClientErrorException
import io.fajarca.project.base.network.exception.NoInternetConnection
import io.fajarca.project.base.network.exception.ServerErrorException
import io.fajarca.project.post.databinding.ActivityPostDetailBinding
import io.fajarca.project.post.di.component.DaggerPostComponent
import io.fajarca.project.post.domain.entity.Post
import javax.inject.Inject

class PostDetailActivity : BaseActivity<ActivityPostDetailBinding>() {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val viewModel by viewModels<PostDetailViewModel> { viewModelFactory }

    override val getViewBinding: (LayoutInflater) -> ActivityPostDetailBinding
        get() = ActivityPostDetailBinding::inflate

    private var postId: Int = 0

    companion object {
        private const val INTENT_KEY_POST_ID = "postId"

        @JvmStatic
        fun start(context: Context, userId: Int) {
            val starter = Intent(context, PostDetailActivity::class.java).apply {
                putExtra(INTENT_KEY_POST_ID, userId)
            }
            context.startActivity(starter)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {

        val apiClientComponent = DaggerApiClientComponent.factory().create()
        val postComponent = DaggerPostComponent
            .builder()
            .apiClientComponent(apiClientComponent)
            .baseComponent((application as BaseApplication).getBaseComponent())
            .build()

        postComponent.postDetailComponent().create().inject(this)

        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        handleIntentArguments()
        observePostDetail()
        viewModel.getPostDetail(postId)
    }

    private fun handleIntentArguments() {
        val extras = intent.extras
        postId = extras?.getInt(INTENT_KEY_POST_ID, 0) ?: 0
    }

    private fun observePostDetail() {
        viewModel.post.observe(this) {
            when (it) {
                ViewState.Loading -> {
                    binding.progressBar.visible()
                }
                is ViewState.Success -> {
                    binding.progressBar.gone()
                    displayPostDetail(it.data)
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

    private fun displayPostDetail(post: Post) {
        with(binding) {
            tvBody.text = post.body
            tvTitle.text = post.title
        }
    }
}