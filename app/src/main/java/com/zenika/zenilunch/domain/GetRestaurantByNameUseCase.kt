package com.zenika.zenilunch.domain

import com.zenika.zenilunch.network.RestaurantDto
import com.zenika.zenilunch.repository.RestaurantRepository
import javax.inject.Inject

class GetRestaurantByNameUseCase @Inject constructor(
    private val restaurantRepository: RestaurantRepository
) {
    suspend operator fun invoke(restaurantName: String): RestaurantDto =
        restaurantRepository.getRestaurants().first { it.name == restaurantName }
}
