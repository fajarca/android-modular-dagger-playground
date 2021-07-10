package io.fajarca.project.navigation

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class UserRouterData(val userId: Int) : Parcelable