package io.henrikhorbovyi.ishelteryou.ui.screen

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.google.maps.android.compose.*
import io.henrikhorbovyi.ishelteryou.R
import io.henrikhorbovyi.ishelteryou.state.HostsUiState
import io.henrikhorbovyi.ishelteryou.state.rememberHostsMapState
import io.henrikhorbovyi.ishelteryou.ui.entity.latLngPosition
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterialApi::class)
@Composable
fun HostsMapScreen(
    onMarkerClicked: (String?) -> Unit = {},
    sheetContent: @Composable () -> Unit = {}
) {
    val hostsMapState = rememberHostsMapState()
    val scope = rememberCoroutineScope()
    var sheetShapeState by remember { mutableStateOf(8.dp) }
    val sheetState = rememberModalBottomSheetState(
        initialValue = ModalBottomSheetValue.Hidden
    )

    LaunchedEffect(sheetState.currentValue) {
        sheetShapeState = if (sheetState.currentValue == ModalBottomSheetValue.Expanded)
            0.dp
        else
            8.dp
    }

    ModalBottomSheetLayout(
        sheetShape = RoundedCornerShape(sheetShapeState),
        sheetState = sheetState,
        sheetContent = { sheetContent() },
        content = {
            Scaffold(
                content = {
                    GoogleMap(
                        modifier = Modifier.fillMaxSize(),
                        cameraPositionState = hostsMapState.mapCameraPositionState,
                        uiSettings = MapUiSettings(zoomControlsEnabled = false)
                    ) {
                        HostsContent(hostUiState = hostsMapState.hostsState, onMarkerClicked = onMarkerClicked)
                    }
                },
                floatingActionButton = {
                    ExtendedFloatingActionButton(
                        onClick = {
                            scope.launch { sheetState.animateTo(ModalBottomSheetValue.Expanded) }
                        },
                        text = { Text("I CAN HELP") },
                        icon = { Icon(painterResource(id = R.drawable.ic_host), contentDescription = "Help button icon") }
                    )
                }
            )
        }
    )
}

@Composable
private fun HostsContent(hostUiState: State<HostsUiState>, onMarkerClicked: (String?) -> Unit = {}) {
    when (val state = hostUiState.value) {
        is HostsUiState.Loading -> {
            // maybe show a progress
        }
        is HostsUiState.Error -> {
            // show an error message to the user
        }
        is HostsUiState.Loaded -> {
            // display all hosts on the map
            state.hosts.forEach { host ->
                Marker(
                    state = MarkerState(position = host.place.latLngPosition()),
                    title = host.place.address,
                    onClick = { onMarkerClicked(host.id); false }
                )
            }
        }
    }
}