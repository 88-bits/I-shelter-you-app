package io.henrikhorbovyi.ishelteryou.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.henrikhorbovyi.data.repository.HostRepository
import io.henrikhorbovyi.ishelteryou.state.HostDetailsUiState
import io.henrikhorbovyi.ishelteryou.ui.entity.toUi
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class HostDetailsViewModel(
    private val hostRepository: HostRepository
) : ViewModel() {

    private var hostDetailsJob: Job? = null
    var hostDetailsState: MutableStateFlow<HostDetailsUiState> = MutableStateFlow(HostDetailsUiState.Loading)
        private set


    fun getDetailsOf(hostId: String?) {
        hostDetailsJob?.cancel()
        hostDetailsJob = viewModelScope.launch {
            hostDetailsState.value = HostDetailsUiState.Loading
            val host = hostRepository.getDetailsOf(hostId)
            hostDetailsState.value = HostDetailsUiState.Loaded(host.toUi())
        }
    }
}