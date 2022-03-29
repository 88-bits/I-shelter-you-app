package io.henrikhorbovyi.ishelteryou.ui.entity

import com.google.android.gms.maps.model.LatLng

data class PlaceUi(
    val country: String = "",
    val city: String = "",
    val address: String = "",
    val postcode: String = "",
    val latitude: Double = 0.0,
    val longitude: Double = 0.0,
    val description: String = "",
    val capacity: Int = 1,
    val isPetFriendly: Boolean = false,
)

fun PlaceUi.latLngPosition(): LatLng = LatLng(latitude, longitude)
