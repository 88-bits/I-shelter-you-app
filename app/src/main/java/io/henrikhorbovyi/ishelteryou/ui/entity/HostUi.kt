package io.henrikhorbovyi.ishelteryou.ui.entity

data class HostUi(
    val id: String? = "",
    val name: String = "",
    val place: PlaceUi = PlaceUi(),
    val contact: ContactUi = ContactUi(),
    val createdAt: String? = "",
    val isFavorite: Boolean = false
) {

    override fun toString(): String {
        return "Hey, $name is offering a safe place for refugees.\n" +
                "Open it on google maps: http://maps.google.com/maps?daddr=${place.address}"
    }
}