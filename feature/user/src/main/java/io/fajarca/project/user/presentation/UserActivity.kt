package io.fajarca.project.user.presentation

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import io.fajarca.project.base.ViewState
import io.fajarca.project.base.abstraction.BaseApplication
import io.fajarca.project.base.extension.gone
import io.fajarca.project.base.extension.visible
import io.fajarca.project.base.network.exception.ClientErrorException
import io.fajarca.project.base.network.exception.NoInternetConnection
import io.fajarca.project.base.network.exception.ServerErrorException
import io.fajarca.project.base.router.Routable
import io.fajarca.project.user.databinding.ActivityUserBinding
import io.fajarca.project.user.di.component.DaggerUserComponent
import javax.inject.Inject

class UserActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory


    private val viewModel by viewModels<UserViewModel> {  viewModelFactory }
    private val binding by lazy { ActivityUserBinding.inflate(layoutInflater) }

    companion object {
        @JvmStatic
        fun start(context: Context) {
            val starter = Intent(context, UserActivity::class.java)
            context.startActivity(starter)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {

        DaggerUserComponent
            .builder()
            .baseComponent((application as BaseApplication).getBaseComponent())
            .build()
            .inject(this)

        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        observeUsers()
        viewModel.getUsers()
        binding.btnGoToPostModule.setOnClickListener {

            /*router.routeToActivity(
                this,
                UserRouterData.ROUTER_ID,
                UserRouterData(1)
            )*/
            //userRouter.navigateToPost(this, "3")
        }

        val extras = intent.extras
        val data = extras?.getParcelable(Routable.ROUTE_DATA) as? UserRouterData ?: return
        val userId = data.userId
    }

    private fun observeUsers() {
        viewModel.users.observe(this){
            when (it) {
                ViewState.Loading -> {
                    binding.progressBar.visible()
                }
                is ViewState.Success -> {
                    binding.tvUsers.text = it.data.toString()
                    binding.progressBar.gone()
                }
                is ViewState.Error -> {
                    binding.progressBar.gone()
                    when(val cause = it.cause) {
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