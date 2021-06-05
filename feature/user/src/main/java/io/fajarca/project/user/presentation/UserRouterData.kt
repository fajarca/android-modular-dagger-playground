package io.fajarca.project.user.presentation

import io.fajarca.project.base.router.RouterData
import kotlinx.android.parcel.Parcelize

@Parcelize
data class UserRouterData(val userId: Int) : RouterData() {
    companion object {
        const val ROUTER_ID = 4
    }
}