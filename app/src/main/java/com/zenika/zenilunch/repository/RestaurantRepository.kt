package com.zenika.zenilunch.repository

import com.zenika.zenilunch.network.RestaurantDto
import com.zenika.zenilunch.network.RestaurantNetwork
import javax.inject.Inject

class RestaurantRepository @Inject constructor(private val restaurantNetwork: RestaurantNetwork) {

    private var cache: List<RestaurantDto>? = null
    suspend fun getRestaurants(): List<RestaurantDto> {
        return when (val cache = cache) {
            null -> restaurantNetwork.getRestaurants().also { this.cache = it }
            else -> cache
        }
    }
}
