package io.henrikhorbovyi.ishelteryou.state

import io.henrikhorbovyi.ishelteryou.ui.entity.HostUi

sealed class HostDetailsUiState {
    object Loading : HostDetailsUiState()
    data class Error(val message: String?) : HostDetailsUiState()
    data class Loaded(val host: HostUi) : HostDetailsUiState()
}