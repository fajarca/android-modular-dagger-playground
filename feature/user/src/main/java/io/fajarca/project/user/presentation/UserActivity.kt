package io.fajarca.project.user.presentation

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.ViewModelProvider
import io.fajarca.project.base.ViewState
import io.fajarca.project.base.abstraction.BaseApplication
import io.fajarca.project.base.network.exception.NoInternetConnection
import io.fajarca.project.user.R
import io.fajarca.project.user.di.component.DaggerUserComponent
import javax.inject.Inject

class UserActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel by viewModels<UserViewModel> {  viewModelFactory }

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
        setContentView(R.layout.activity_user)

        viewModel.setPin("123456")
        val pin = viewModel.getPin()



        viewModel.getUsers()
        viewModel.users.observe(this){
            when (it) {
                ViewState.Loading -> {

                }
                is ViewState.Success -> {

                }
                is ViewState.Error -> {
                    when (it.cause) {
                        is NoInternetConnection -> {
                            Toast.makeText(this, "No connection", Toast.LENGTH_LONG).show()
                        }
                    }
                }
            }
        }
    }

}