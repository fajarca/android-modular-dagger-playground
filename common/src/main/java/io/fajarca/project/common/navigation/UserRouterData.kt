package io.fajarca.project.common.navigation

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class UserRouterData(val userId: Int) : Parcelable