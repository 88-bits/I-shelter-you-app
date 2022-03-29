package io.henrikhorbovyi.ishelteryou.state

import io.henrikhorbovyi.ishelteryou.ui.entity.HostUi

sealed class HostsUiState {
    object Loading : HostsUiState()
    data class Error(val message: String?) : HostsUiState()
    data class Loaded(val hosts: List<HostUi>) : HostsUiState()
}