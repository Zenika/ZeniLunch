package com.zenika.zenilunch.domain

import com.zenika.zenilunch.data.network.RestaurantDto
import com.zenika.zenilunch.repository.AgencyRepository
import com.zenika.zenilunch.repository.RestaurantRepository
import javax.inject.Inject

class GetRestaurantByNameUseCase @Inject constructor(
    private val restaurantRepository: RestaurantRepository,
    private val agencyRepository: AgencyRepository
) {
    suspend operator fun invoke(restaurantName: String): RestaurantDto {
        val agency = agencyRepository.getSelectedAgency()
        return restaurantRepository.getRestaurants(agency)
            .first { it.name == restaurantName }
    }
}
