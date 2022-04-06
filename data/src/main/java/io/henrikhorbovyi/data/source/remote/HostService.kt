package io.henrikhorbovyi.data.source.remote

import io.henrikhorbovyi.data.source.remote.model.HostRemote
import io.henrikhorbovyi.data.source.remote.model.HostResponse
import retrofit2.http.*

interface HostService {

    @GET("/hosts")
    suspend fun fetchAll(): HostResponse

    @POST("/hosts")
    suspend fun publish(@Body host: HostRemote)

    @GET("/hosts/{id}")
    suspend fun getById(@Path("id") hostId: String): HostRemote

}
