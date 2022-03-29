package io.henrikhorbovyi.data.source.local.entity

import android.location.Address
import android.location.Geocoder

class LocationClient(
    private val geocoder: Geocoder
) {

    fun getLatitudeLongitudeByAddress(addressName: String): Pair<Double, Double> {
        val address: Address = geocoder.getFromLocationName(addressName, 1).first()
        return address.latitude to address.longitude
    }
}