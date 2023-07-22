package com.zenika.zenilunch.data.network

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class RestaurantDto(
    @field:Json(name = "name") val name: String,
    @field:Json(name = "type") val type: String,
    @field:Json(name = "price") val price: String,
    @field:Json(name = "vegetarian") val vegetarian: Boolean,
    @field:Json(name = "vegan") val vegan: Boolean,
    @field:Json(name = "latitude") val latitude: Double,
    @field:Json(name = "longitude") val longitude: Double
)
