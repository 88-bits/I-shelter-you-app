package io.henrikhorbovyi.ishelteryou.navigation

import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavType
import androidx.navigation.navArgument

abstract class Screen(protected val baseRoute: String) {
    open val route: String = baseRoute
}


object Hosts : Screen("hosts")

object HostDetails : Screen("host-details") {
    private const val paramHostId = "hostId"

    override val route: String
        get() = "${this.baseRoute}/{$paramHostId}"

    val navArguments = listOf(
        navArgument(paramHostId) { type = NavType.StringType },
    )
    fun getHostId(backStackEntry: NavBackStackEntry): String? =
        backStackEntry.arguments?.getString(paramHostId)

    fun buildRoute(id: String?) = "${this.baseRoute}/$id"
}
