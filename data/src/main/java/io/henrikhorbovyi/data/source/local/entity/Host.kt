package io.henrikhorbovyi.data.source.local.entity

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import io.henrikhorbovyi.data.source.remote.model.ContactRemote
import io.henrikhorbovyi.data.source.remote.model.HostRemote
import io.henrikhorbovyi.data.source.remote.model.PlaceRemote

@Entity(tableName = "host")
data class Host(
    @PrimaryKey(autoGenerate = false)
    val id: String,
    val name: String,
    @Embedded
    val place: Place,
    @Embedded
    val contact: Contact,
    val createdAt: String,
)

fun Host.toRemote(): HostRemote = HostRemote(
    id = this.id.ifEmpty { null },
    name = this.name,
    contact = this.contact.toRemote(),
    place = this.place.toRemote(),
    createdAt = null
)

fun Contact.toRemote(): ContactRemote = ContactRemote(
    phone = this.phone,
    email = this.email
)

fun Place.toRemote(): PlaceRemote = PlaceRemote(
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
