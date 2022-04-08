package io.henrikhorbovyi.ishelteryou.navigation

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetState
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import io.henrikhorbovyi.ishelteryou.intent.openOnMap
import io.henrikhorbovyi.ishelteryou.intent.sendEmail
import io.henrikhorbovyi.ishelteryou.intent.sendMessage
import io.henrikhorbovyi.ishelteryou.ui.screen.AddHostScreen
import io.henrikhorbovyi.ishelteryou.ui.screen.HostDetailScreen
import io.henrikhorbovyi.ishelteryou.ui.screen.HostsMapScreen
import io.henrikhorbovyi.ishelteryou.viewmodel.HostsViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
fun NavGraphBuilder.mainGraph(
    navController: NavController,
    hostsViewModel: HostsViewModel,
    sheetState: ModalBottomSheetState,
    scope: CoroutineScope,
) {
    composable("hosts") {
        HostsMapScreen(
            onMarkerClicked = { hostId -> navController.navigate("details/$hostId") },
            sheetState = sheetState,
            sheetContent = {
                AddHostScreen(
                    onPublishClicked = hostsViewModel::publishHost,
                    onBackPressed = { scope.launch { sheetState.hide() } }
                )
            },
        )
    }
    composable(
        route = "details/{hostId}",
        arguments = listOf(navArgument("hostId") { type = NavType.StringType })
    ) {
        val hostId = it.arguments?.getString("hostId")
        val context = LocalContext.current

        HostDetailScreen(
            hostId = hostId,
            hostsViewModel = hostsViewModel,
            onSendMessage = { phone -> context.sendMessage(phone) },
            onSendEmail = { email -> context.sendEmail(email) },
            onOpenMap = { latLng -> context.openOnMap(latLng) }
        )
    }
}