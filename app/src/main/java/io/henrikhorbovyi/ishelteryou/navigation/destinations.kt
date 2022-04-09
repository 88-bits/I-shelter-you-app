package io.henrikhorbovyi.ishelteryou.navigation

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetState
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavBackStackEntry
import io.henrikhorbovyi.ishelteryou.intent.openOnMap
import io.henrikhorbovyi.ishelteryou.intent.sendEmail
import io.henrikhorbovyi.ishelteryou.intent.sendMessage
import io.henrikhorbovyi.ishelteryou.intent.share
import io.henrikhorbovyi.ishelteryou.ui.screen.AddHostScreen
import io.henrikhorbovyi.ishelteryou.ui.screen.HostDetailScreen
import io.henrikhorbovyi.ishelteryou.ui.screen.HostsMapScreen
import io.henrikhorbovyi.ishelteryou.viewmodel.HostDetailsViewModel
import io.henrikhorbovyi.ishelteryou.viewmodel.HostsViewModel
import kotlinx.coroutines.launch
import org.koin.androidx.compose.inject

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun HostsDestination(onDetailNavigation: (hostId: String?) -> Unit) {
    val hostsViewModel: HostsViewModel by inject()
    val scope = rememberCoroutineScope()
    val sheetState: ModalBottomSheetState by lazy {
        ModalBottomSheetState(initialValue = ModalBottomSheetValue.Hidden)
    }

    HostsMapScreen(
        onMarkerClicked = onDetailNavigation,
        sheetState = sheetState,
        sheetContent = {
            AddHostScreen(
                onPublishClicked = hostsViewModel::publishHost,
                onBackPressed = { scope.launch { sheetState.hide() } }
            )
        },
    )
}

@Composable
fun HostDetailsDestination(
    backStackEntry: NavBackStackEntry
) {
    val context = LocalContext.current
    val hostId = HostDetails.getHostId(backStackEntry)
    val hostsDetailsViewModel: HostDetailsViewModel by inject()
    HostDetailScreen(
        hostId = hostId,
        hostDetailsViewModel = hostsDetailsViewModel,
        onShareClicked =  { context.share(it) },
        onSendMessage = { context.sendMessage(it) },
        onSendEmail = { context.sendEmail(it) },
        onOpenMap = { context.openOnMap(it) }
    )
}