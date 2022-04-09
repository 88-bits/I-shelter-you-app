package io.henrikhorbovyi.ishelteryou.navigation

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetState
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import io.henrikhorbovyi.ishelteryou.viewmodel.HostsViewModel
import kotlinx.coroutines.CoroutineScope

@OptIn(ExperimentalMaterialApi::class)
fun NavGraphBuilder.mainGraph(
    navController: NavController,
    hostsViewModel: HostsViewModel,
    sheetState: ModalBottomSheetState,
    scope: CoroutineScope,
) {
    composable(Hosts.route) {
        HostsDestination(
            onDetailNavigation = { navController.navigate(HostDetails.buildRoute(it)) }
        )
    }
    composable(
        route = HostDetails.route,
        arguments = HostDetails.navArguments
    ) { HostDetailsDestination(backStackEntry = it) }
}
