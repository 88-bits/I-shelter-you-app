package io.henrikhorbovyi.ishelteryou.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.henrikhorbovyi.data.repository.HostRepository
import io.henrikhorbovyi.ishelteryou.state.HostUiState
import io.henrikhorbovyi.ishelteryou.state.HostsUiState
import io.henrikhorbovyi.ishelteryou.ui.entity.HostUi
import io.henrikhorbovyi.ishelteryou.ui.entity.toLocal
import io.henrikhorbovyi.ishelteryou.ui.entity.toUi
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class HostsViewModel(
    private val hostRepository: HostRepository
) : ViewModel() {

    private var publishHostJob: Job? = null
    private var publishHostValidation: MutableStateFlow<Boolean?> = MutableStateFlow(null)

    private var hostByIdJob: Job? = null
    var hostByIdState: MutableStateFlow<HostUiState> = MutableStateFlow(HostUiState.Loading)
        private set

    val hostsState: StateFlow<HostsUiState> = flow {
        hostRepository.fetchAll()
            .catch { emit(HostsUiState.Error(it.message)) }
            .collect { emit(HostsUiState.Loaded(it.toUi())) }
    }.stateIn(
        initialValue = HostsUiState.Loading,
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed()
    )

    fun getHostById(hostId: String?) {
        hostByIdJob?.cancel()
        hostByIdJob = viewModelScope.launch {
            hostByIdState.value = HostUiState.Loading
            val host = hostRepository.getById(hostId)
            hostByIdState.value = HostUiState.Loaded(host.toUi())
        }
    }

    fun publishHost(host: HostUi) {
        publishHostJob?.cancel()
        publishHostJob = viewModelScope.launch {
            val isValid = validate(host)
            if (isValid) hostRepository.publish(host.toLocal())
            publishHostValidation.value = isValid
        }
    }

    private fun validate(host: HostUi): Boolean {
        // TODO: implement validation
        return true
    }
}
