package com.zenika.zenilunch.agency.model

data class Agency(
    val id: String,
    val name: String,
    val logoUrl: String,
    val restaurantsUrlPath: String,
    val location: LatLng
)
