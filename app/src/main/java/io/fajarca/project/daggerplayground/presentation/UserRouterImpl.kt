package io.fajarca.project.daggerplayground.presentation

import android.content.Context
import android.content.Intent
import io.fajarca.project.post.PostActivity
import io.fajarca.project.base.router.UserRouter
import javax.inject.Inject

class UserRouterImpl @Inject constructor() : UserRouter {
    override fun navigateToPost(context : Context, id: String) {
        val intent = Intent(context, PostActivity::class.java).apply {
            putExtra("id", id)
        }
        context.startActivity(intent)
    }
}