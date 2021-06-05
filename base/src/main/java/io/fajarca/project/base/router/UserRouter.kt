package io.fajarca.project.base.router

import android.content.Context

interface UserRouter {
    fun navigateToPost(context: Context, id : String)
}