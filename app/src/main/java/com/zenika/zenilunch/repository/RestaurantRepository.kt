package com.zenika.zenilunch.repository

import com.zenika.zenilunch.agency.model.Agency
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

    suspend fun getRestaurants(agency: Agency): List<RestaurantDto> {
        return when (val cache = cache) {
            null -> restaurantNetwork.getRestaurants(agency).also { this.cache = it }
            else -> cache
        }
    }

    suspend fun getUnhiddenRestaurants(agency: Agency): List<RestaurantDto> {
        val hiddenRestaurants = restaurantDao.getHiddenRestaurants()
        return getRestaurants(agency).filter { restaurant ->
            hiddenRestaurants.none { hidden -> hidden.name == restaurant.name }
        }
    }

    suspend fun addHiddenRestaurant(restaurantName: String) {
        val restaurant = HiddenRestaurant(restaurantName, Date())
        restaurantDao.upsertRestaurant(restaurant)
    }

    fun clearCache() {
        cache = emptyList()
    }
}
