package io.henrikhorbovyi.ishelteryou.state

import androidx.compose.runtime.*
import androidx.compose.ui.unit.dp
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.rememberCameraPositionState
import io.henrikhorbovyi.ishelteryou.viewmodel.HostsViewModel
import org.koin.androidx.compose.viewModel

@Composable
fun rememberHostsMapState(): HostsState {
    val hostsViewModel: HostsViewModel by viewModel()
    return remember { HostsState(hostsViewModel) }
}

@Stable
class HostsState(
    private val hostsViewModel: HostsViewModel
) {

    val hostsState
        @Composable get() = hostsViewModel
            .hostsState
            .collectAsState(initial = HostsUiState.Loading)

    private val europe = LatLng(49.9199, 15.7562)

    val mapCameraPositionState
        @Composable get() = rememberCameraPositionState {
            position = CameraPosition.fromLatLngZoom(europe, 1f)
        }
}
