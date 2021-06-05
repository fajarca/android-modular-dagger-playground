package io.fajarca.project.user.presentation

import io.fajarca.project.base.router.Routable

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