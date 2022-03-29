package io.henrikhorbovyi.data.source.remote.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PlaceRemote(
    val country: String,
    val city: String,
    val address: String,
    val postcode: String,
    val latitude: Double,
    val longitude: Double,
    val description: String,
    val capacity: Int,
    val isPetFriendly: Boolean
)