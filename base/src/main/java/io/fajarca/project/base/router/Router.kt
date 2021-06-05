package io.fajarca.project.base.router

import android.content.Context
import android.net.Uri
import android.util.SparseArray
import androidx.core.util.forEach
import javax.inject.Inject

class Router @Inject constructor(
    private val routes: SparseArray<Routable<*, *>>
) {
    /**
     * Routes with given code & bundle to the route
     * which contains the given code as deep link code
     */
    fun routeToLink(context: Context, code: Int, data: Uri?) {
        routes.get(code)?.startDeeplink(context, data)
    }

    /**
     * Routes with given code & data to the route
     * which contains the given code as deep link code
     * with given rotuer
     * O(1) time complexity way
     */
    fun routeToActivity(context: Context, code: Int, data: RouterData?) {
        routes.get(code)?.startActivity(context, data)
    }

    /**
     * Routes with given data
     * which contains the given data class type as router data
     * O(n) time complexity way
     */
    fun routeToActivity(context: Context, data: RouterData?) {
        routes.forEach { _, routable ->
            routable.takeIf { it.routerDataClass == data?.javaClass }?.startActivity(context, data)
        }
    }
}