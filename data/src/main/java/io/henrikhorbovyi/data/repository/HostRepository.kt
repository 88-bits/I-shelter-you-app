package io.henrikhorbovyi.data.repository

import io.henrikhorbovyi.data.source.local.entity.Host
import kotlinx.coroutines.flow.Flow

interface HostRepository {
    fun fetchAll(): Flow<List<Host>>
    suspend fun publish(host: Host)
    suspend fun getById(hostId: String?): Host
}