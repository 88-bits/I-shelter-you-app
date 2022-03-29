package io.henrikhorbovyi.data.source.remote.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ContactRemote(
    val phone: String,
    val email: String
)