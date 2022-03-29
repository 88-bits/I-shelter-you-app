package io.henrikhorbovyi.data.source.local.entity

data class Place(
    val country: String,
    val city: String,
    val address: String,
    val postcode: String,
    val latitude: Double,
    val longitude: Double,
    val description: String,
    val capacity: Int,
    val isPetFriendly: Boolean,
    val fullAddress: String = "$address, $city, $postcode, $country"
)