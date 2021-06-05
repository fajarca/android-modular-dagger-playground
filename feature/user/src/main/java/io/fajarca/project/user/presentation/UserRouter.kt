package io.fajarca.project.user.presentation

import io.fajarca.project.base.router.Routable

class UserRouter {

    companion object : Routable<UserActivity, io.fajarca.project.common.route.UserRouterData> {
        override val route: Class<UserActivity>
            get() = UserActivity::class.java
        override val routerDataClass: Class<io.fajarca.project.common.route.UserRouterData>
            get() = io.fajarca.project.common.route.UserRouterData::class.java
        override val deepLinkCode: Int
            get() = 2

    }

}