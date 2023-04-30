package com.zenika.zenilunch.domain

import com.zenika.zenilunch.network.RestaurantDto
import com.zenika.zenilunch.repository.RestaurantRepository
import javax.inject.Inject

class GetRestaurantsSortedByNameUseCase @Inject constructor(
    private val restaurantRepository: RestaurantRepository
) {
    suspend operator fun invoke(): List<RestaurantDto> =
        restaurantRepository.getRestaurants().sortedBy { it.name }
}
