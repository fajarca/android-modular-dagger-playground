package io.fajarca.project.user.presentation.list

import io.fajarca.project.core.router.Routable
import io.fajarca.project.navigation.UserRouterData

class UserRouter {

    companion object : Routable<UserActivity, UserRouterData> {
        override val route: Class<UserActivity>
            get() = UserActivity::class.java
        override val routerDataClass: Class<UserRouterData>
            get() = UserRouterData::class.java
        override val deepLinkCode: Int
            get() = 2

    }

}