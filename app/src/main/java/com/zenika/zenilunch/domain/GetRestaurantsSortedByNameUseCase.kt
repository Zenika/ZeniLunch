package com.zenika.zenilunch.domain

import com.zenika.zenilunch.data.network.RestaurantDto
import com.zenika.zenilunch.repository.AgencyRepository
import com.zenika.zenilunch.repository.RestaurantRepository
import javax.inject.Inject

class GetRestaurantsSortedByNameUseCase @Inject constructor(
    private val restaurantRepository: RestaurantRepository,
    private val agencyRepository: AgencyRepository
) {
    suspend operator fun invoke(): List<RestaurantDto> {
        val agency = agencyRepository.getSelectedAgency()
        return restaurantRepository.getUnhiddenRestaurants(agency)
            .sortedBy { it.name }
    }
}
