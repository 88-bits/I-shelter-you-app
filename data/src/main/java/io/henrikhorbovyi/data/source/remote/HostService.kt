package io.henrikhorbovyi.data.source.remote

import io.henrikhorbovyi.data.source.remote.model.HostRemote
import io.henrikhorbovyi.data.source.remote.model.HostResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface HostService {

    @GET("/hosts")
    suspend fun fetchAll(): HostResponse

    @POST("/hosts")
    suspend fun publish(@Body host: HostRemote)
}
