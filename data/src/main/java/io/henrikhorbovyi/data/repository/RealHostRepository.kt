package io.henrikhorbovyi.data.repository

import io.henrikhorbovyi.data.source.local.HostDao
import io.henrikhorbovyi.data.source.local.entity.Host
import io.henrikhorbovyi.data.source.local.entity.LocationClient
import io.henrikhorbovyi.data.source.local.entity.toRemote
import io.henrikhorbovyi.data.source.remote.HostService
import io.henrikhorbovyi.data.source.remote.model.toLocal
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flow

class RealHostRepository(
    private val hostService: HostService,
    private val hostDao: HostDao,
    private val locationClient: LocationClient
) : HostRepository {

    override fun fetchAll(): Flow<List<Host>> = flow {
        val response = hostService.fetchAll()

        hostDao.storeHosts(*response.hosts.toLocal().toTypedArray())

        val persistedHosts = hostDao.fetchAllHosts()
        emitAll(persistedHosts)
    }

    override suspend fun publish(host: Host) {
        val latLng = locationClient.getLatitudeLongitudeByAddress(host.place.fullAddress)
        val hostWithLatLng = host
            .copy(
                place = host.place.copy(
                    latitude = latLng.first,
                    longitude = latLng.second
                )
            )

        hostService.publish(hostWithLatLng.toRemote())
        hostDao.storeHosts(hostWithLatLng)
    }

    override suspend fun getDetailsOf(hostId: String?): Host {
        return hostService.getHostById(hostId ?: "").toLocal()
    }
}
