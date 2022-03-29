package io.henrikhorbovyi.ishelteryou.ui.entity

import io.henrikhorbovyi.data.source.local.entity.Contact
import io.henrikhorbovyi.data.source.local.entity.Host
import io.henrikhorbovyi.data.source.local.entity.Place

fun List<Host>.toUi(): List<HostUi> = map { it.toUi() }

fun Host.toUi(): HostUi = HostUi(
    id = this.id,
    name = this.name,
    place = this.place.toUi(),
    contact = this.contact.toUi(),
    createdAt = this.createdAt,
)

fun Place.toUi(): PlaceUi = PlaceUi(
    country = this.country,
    city = this.city,
    address = this.address,
    postcode = this.postcode,
    latitude = this.latitude,
    longitude = this.longitude,
    description = this.description,
    capacity = this.capacity,
    isPetFriendly = this.isPetFriendly
)

fun Contact.toUi(): ContactUi = ContactUi(
    phone = this.phone,
    email = this.email
)

fun HostUi.toLocal(): Host = Host(
    id = this.id.orEmpty(),
    name = this.name,
    place = this.place.toLocal(),
    contact = this.contact.toLocal(),
    createdAt = this.createdAt.orEmpty(),
)

fun PlaceUi.toLocal(): Place = Place(
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
fun ContactUi.toLocal(): Contact = Contact(
    phone = this.phone,
    email = this.email
)
