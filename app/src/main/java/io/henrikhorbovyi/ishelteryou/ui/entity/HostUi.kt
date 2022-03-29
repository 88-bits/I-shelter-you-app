package io.henrikhorbovyi.ishelteryou.ui.entity

data class HostUi(
    val id: String? = "",
    val name: String = "",
    val place: PlaceUi = PlaceUi(),
    val contact: ContactUi = ContactUi(),
    val createdAt: String? = "",
)