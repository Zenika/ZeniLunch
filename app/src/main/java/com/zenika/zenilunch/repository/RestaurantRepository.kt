package com.zenika.zenilunch.repository

import com.zenika.zenilunch.data.HiddenRestaurant
import com.zenika.zenilunch.data.RestaurantDao
import com.zenika.zenilunch.network.RestaurantDto
import com.zenika.zenilunch.network.RestaurantNetwork
import java.util.Date
import javax.inject.Inject

class RestaurantRepository @Inject constructor(
    private val restaurantDao: RestaurantDao,
    private val restaurantNetwork: RestaurantNetwork
) {
    private var cache: List<RestaurantDto>? = null

    suspend fun getRestaurants(): List<RestaurantDto> {
        return when (val cache = cache) {
            null -> restaurantNetwork.getRestaurants().also { this.cache = it }
            else -> cache
        }
    }

    suspend fun getHiddenRestaurants(): List<HiddenRestaurant> {
        return restaurantDao.getHiddenRestaurants()
    }

    suspend fun addHiddenRestaurant(restaurantName: String) {
        val restaurant = HiddenRestaurant(restaurantName, Date())
        restaurantDao.upsertRestaurant(restaurant)
    }
}
