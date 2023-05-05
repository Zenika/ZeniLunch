package com.zenika.zenilunch.domain

import com.zenika.zenilunch.agency.model.Agency
import com.zenika.zenilunch.repository.AgencyRepository
import com.zenika.zenilunch.repository.RestaurantRepository
import javax.inject.Inject

class SelectAgencyUseCase @Inject constructor(
    private val agencyRepository: AgencyRepository,
    private val restaurantRepository: RestaurantRepository
) {
    suspend operator fun invoke(agency: Agency) {
        restaurantRepository.clearCache()
        agencyRepository.setSelectedAgency(agency)
    }
}
