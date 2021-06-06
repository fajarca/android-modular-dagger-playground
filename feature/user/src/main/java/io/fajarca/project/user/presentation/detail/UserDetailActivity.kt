package io.fajarca.project.user.presentation.detail

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.ViewModelProvider
import io.fajarca.project.base.abstraction.BaseApplication
import io.fajarca.project.base.router.Routable
import io.fajarca.project.common.route.UserRouterData
import io.fajarca.project.user.R
import io.fajarca.project.user.databinding.ActivityUserBinding
import io.fajarca.project.user.databinding.ActivityUserDetailBinding
import io.fajarca.project.user.di.component.DaggerUserComponent
import io.fajarca.project.user.presentation.main.UserViewModel
import javax.inject.Inject

class UserDetailActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val viewModel by viewModels<UserDetailViewModel> {  viewModelFactory }

    private val binding by lazy { ActivityUserDetailBinding.inflate(layoutInflater) }
    private var userId : Int = 0

    companion object {
        private const val INTENT_KEY_USER_ID = "userId"

        @JvmStatic
        fun start(context: Context, userId : Int) {
            val starter = Intent(context, UserDetailActivity::class.java).apply {
                putExtra(INTENT_KEY_USER_ID, userId)
            }
            context.startActivity(starter)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        val userComponent = DaggerUserComponent
            .builder()
            .baseComponent((application as BaseApplication).getBaseComponent())
            .build()

        userComponent.userDetailComponent().create().inject(this)

        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        handleIntentArguments()
        viewModel.getUserDetail(userId)
    }

    private fun handleIntentArguments() {
        val extras = intent.extras
        userId = extras?.getInt(INTENT_KEY_USER_ID, 0) ?: 0
    }
}