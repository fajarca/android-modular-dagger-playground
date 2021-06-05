package io.fajarca.project.base.router

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Parcelable
import android.util.Log
import androidx.appcompat.app.AppCompatActivity

/**
 * Routable implementation provides usage of navigation between activities
 * also provides deeplink implementation
 */
interface Routable<Route : AppCompatActivity, Data : Parcelable> {

    companion object {
        const val ROUTE_DATA = "RouteData"
        const val DEEPLINK_DATA = "DeeplinkData"
    }

    val route : Class<Route>
    val routerDataClass : Class<Data>
    val deepLinkCode: Int

    /**
     * Triggers when context comes from deep link navigation
     */
    fun startDeeplink(context: Context, data: Uri?) {
        buildIntent(context)
            .putExtra(DEEPLINK_DATA, data).also {
            context.startActivity(it)
        }
    }

    /**
     * Calling by developer from the code when navigating between activities
     */
    fun startActivity(context : Context, data : Parcelable?) {
        data.takeIf { it?.javaClass == routerDataClass }?.let { routerData ->
            buildIntent(context).putExtra(ROUTE_DATA, routerData).also { intent -> context.startActivity(intent) }
        } ?: kotlin.run {
            Log.e(javaClass.simpleName, "router data is not valid!")
        }
    }

    /**
     * Build default intent
     * Override it when you need a custom intent
     */
    fun buildIntent(context: Context?) = Intent(context, route)
}