package io.henrikhorbovyi.ishelteryou.ui.screen

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.rememberCoroutineScope
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import io.henrikhorbovyi.ishelteryou.intent.openOnMap
import io.henrikhorbovyi.ishelteryou.intent.sendEmail
import io.henrikhorbovyi.ishelteryou.intent.sendMessage
import io.henrikhorbovyi.ishelteryou.ui.theme.AppTheme
import io.henrikhorbovyi.ishelteryou.viewmodel.HostsViewModel
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

@OptIn(
    ExperimentalMaterial3Api::class,
    ExperimentalMaterialApi::class
)
class MainActivity : ComponentActivity() {

    private val hostsViewModel: HostsViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            val sheetState = rememberModalBottomSheetState(initialValue = ModalBottomSheetValue.Hidden)
            val scope = rememberCoroutineScope()

            AppTheme {
                NavHost(navController = navController, startDestination = "hosts") {
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
                        HostDetailScreen(
                            hostId = hostId,
                            hostsViewModel = hostsViewModel,
                            onSendMessage = ::sendMessage,
                            onSendEmail = ::sendEmail,
                            onOpenMap = ::openOnMap
                        )
                    }
                }
            }
        }
    }
}
