package io.henrikhorbovyi.data.source.remote.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import io.henrikhorbovyi.data.source.local.entity.Contact
import io.henrikhorbovyi.data.source.local.entity.Host
import io.henrikhorbovyi.data.source.local.entity.Place

@JsonClass(generateAdapter = true)
data class HostRemote(
    @Json(name = "_id")
    val id: String? = null,
    val name: String,
    val contact: ContactRemote,
    val place: PlaceRemote,
    val createdAt: String? = null
)

fun List<HostRemote>.toLocal(): List<Host> = map { it.toLocal() }

fun HostRemote.toLocal(): Host = Host(
    id = this.id.orEmpty(),
    name = this.name,
    contact = this.contact.toLocal(),
    place = this.place.toLocal(),
    createdAt = this.createdAt.orEmpty()
)

fun ContactRemote.toLocal(): Contact = Contact(
    phone = this.phone,
    email = this.email
)

fun PlaceRemote.toLocal(): Place = Place(
    country = this.country,
    city = this.city,
    address = this.address,
    postcode = this.postcode,
    latitude = this.latitude,
    longitude = this.longitude,
    description = this.description,
    capacity = this.capacity,
    isPetFriendly = this.isPetFriendly,
)
