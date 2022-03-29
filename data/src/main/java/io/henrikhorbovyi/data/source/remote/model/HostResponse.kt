package io.henrikhorbovyi.data.source.remote.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class HostResponse(val hosts: List<HostRemote>)
