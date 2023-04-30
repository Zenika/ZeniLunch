package com.zenika.zenilunch.domain

import com.zenika.zenilunch.repository.RestaurantRepository
import javax.inject.Inject

class HideRestaurantUseCase @Inject constructor(
    private val restaurantRepository: RestaurantRepository
) {
    suspend operator fun invoke(restaurantName: String) {
        restaurantRepository.addHiddenRestaurant(restaurantName)
    }
}
