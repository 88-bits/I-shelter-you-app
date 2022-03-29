package io.henrikhorbovyi.ishelteryou.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.henrikhorbovyi.data.repository.HostRepository
import io.henrikhorbovyi.ishelteryou.state.HostsUiState
import io.henrikhorbovyi.ishelteryou.ui.entity.HostUi
import io.henrikhorbovyi.ishelteryou.ui.entity.toLocal
import io.henrikhorbovyi.ishelteryou.ui.entity.toUi
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class HostsViewModel(
    private val hostRepository: HostRepository
) : ViewModel() {

    private var publishHostJob: Job? = null
    private var publishHostValidation: MutableStateFlow<Boolean?> = MutableStateFlow(null)

    val hostsState: StateFlow<HostsUiState> = flow {
        hostRepository.fetchAll()
            .catch { emit(HostsUiState.Error(it.message)) }
            .collect { emit(HostsUiState.Loaded(it.toUi())) }
    }.stateIn(
        initialValue = HostsUiState.Loading,
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed()
    )

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
