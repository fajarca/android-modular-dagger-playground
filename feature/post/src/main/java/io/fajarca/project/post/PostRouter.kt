package io.fajarca.project.post

import android.content.Context
import android.net.Uri
import io.fajarca.project.base.router.Routable

class PostRouter {

    companion object : Routable<PostActivity, io.fajarca.project.common.route.PostRouterData> {

        override val route: Class<PostActivity>
            get() = PostActivity::class.java
        override val routerDataClass: Class<io.fajarca.project.common.route.PostRouterData>
            get() = io.fajarca.project.common.route.PostRouterData::class.java
        override val deepLinkCode: Int
            get() = 1

        override fun startDeeplink(context: Context, data: Uri?) {
            val id  : Int = data?.getQueryParameter("id")?.toInt() ?: 0
            startActivity(context, io.fajarca.project.common.route.PostRouterData(id))
        }


    }

}