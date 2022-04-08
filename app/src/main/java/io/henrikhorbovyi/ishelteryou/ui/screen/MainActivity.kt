package io.henrikhorbovyi.ishelteryou.ui.screen

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.rememberCoroutineScope
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import io.henrikhorbovyi.ishelteryou.navigation.mainGraph
import io.henrikhorbovyi.ishelteryou.ui.theme.AppTheme
import io.henrikhorbovyi.ishelteryou.viewmodel.HostsViewModel
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
            val sheetState =
                rememberModalBottomSheetState(initialValue = ModalBottomSheetValue.Hidden)
            val scope = rememberCoroutineScope()

            AppTheme {
                NavHost(navController = navController, startDestination = "hosts") {
                    mainGraph(
                        navController = navController,
                        hostsViewModel = hostsViewModel,
                        sheetState = sheetState,
                        scope = scope,
                    )
                }
            }
        }
    }
}
