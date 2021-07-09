package io.fajarca.project.post.presentation.list

import android.content.Context
import android.net.Uri
import io.fajarca.project.core.router.Routable
import io.fajarca.project.common.navigation.PostRouterData

class PostRouter {

    companion object : Routable<PostActivity, PostRouterData> {

        override val route: Class<PostActivity>
            get() = PostActivity::class.java
        override val routerDataClass: Class<PostRouterData>
            get() = PostRouterData::class.java
        override val deepLinkCode: Int
            get() = 1

        override fun startDeeplink(context: Context, data: Uri?) {
            val id  : Int = data?.getQueryParameter("id")?.toInt() ?: 0
            startActivity(context, PostRouterData(id))
        }


    }

}