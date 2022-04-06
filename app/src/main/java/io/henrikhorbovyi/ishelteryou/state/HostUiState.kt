package io.henrikhorbovyi.ishelteryou.state

import io.henrikhorbovyi.ishelteryou.ui.entity.HostUi

sealed class HostUiState {
    object Loading : HostUiState()
    data class Error(val message: String?) : HostUiState()
    data class Loaded(val host: HostUi) : HostUiState()
}