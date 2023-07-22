package com.zenika.zenilunch.domain

import com.zenika.zenilunch.data.network.RestaurantDto
import com.zenika.zenilunch.repository.AgencyRepository
import com.zenika.zenilunch.repository.RestaurantRepository
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.toImmutableList

import javax.inject.Inject
import kotlin.math.min

const val NB_SUGGESTIONS_DESIRED = 3

class GetSuggestionsUseCase @Inject constructor(
    private val restaurantRepository: RestaurantRepository,
    private val agencyRepository: AgencyRepository

) {
    suspend operator fun invoke(): ImmutableList<RestaurantDto> {
        val agency = agencyRepository.getSelectedAgency()
        val restaurants = restaurantRepository.getUnhiddenRestaurants(agency)
        return getRandomRestaurants(restaurants)
    }

    private fun getRandomRestaurants(restaurants: List<RestaurantDto>): ImmutableList<RestaurantDto> {
        return restaurants.shuffled()
            .subList(0, min(NB_SUGGESTIONS_DESIRED, restaurants.size))
            .toImmutableList()
    }

}
