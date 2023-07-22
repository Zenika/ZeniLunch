package com.zenika.zenilunch

import com.zenika.zenilunch.agency.model.Agency
import com.zenika.zenilunch.agency.model.LatLng
import com.zenika.zenilunch.domain.GetRestaurantsSortedByNameUseCase
import com.zenika.zenilunch.data.network.RestaurantDto
import com.zenika.zenilunch.repository.AgencyRepository
import com.zenika.zenilunch.repository.RestaurantRepository
import io.mockk.coEvery
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@OptIn(ExperimentalCoroutinesApi::class)
@ExtendWith(MockKExtension::class)
internal class GetRestaurantsSortedByNameUseCaseTest {

    @MockK
    lateinit var agencyRepository: AgencyRepository

    @MockK
    lateinit var restaurantRepository: RestaurantRepository

    @InjectMockKs
    lateinit var getRestaurantsOrderedByName: GetRestaurantsSortedByNameUseCase

    private val chezOlivier = RestaurantDto(
        "Chez Olivier",
        type = "Dev Android",
        price = "",
        vegetarian = false,
        vegan = false,
        latitude = .0,
        longitude = .0
    )

    private val chezAudrey = RestaurantDto(
        "Chez Audrey",
        type = "Dev Android Stagiaire",
        price = "",
        vegetarian = false,
        vegan = false,
        latitude = .0,
        longitude = .0
    )

    @Test
    @DisplayName("Should observe ascendant order list")
    fun shouldObserveOrder() {
        runTest {
            // Given
            coEvery { agencyRepository.getSelectedAgency() } returns Agency(
                "id",
                "name",
                "logoUrl",
                "path",
                LatLng(0.0, 0.0)
            )
            coEvery { restaurantRepository.getUnhiddenRestaurants(any()) } returns listOf(
                chezOlivier,
                chezAudrey
            )

            // When
            val restaurants = getRestaurantsOrderedByName()

            // Then
            assertEquals(
                listOf(chezAudrey, chezOlivier),
                restaurants,
                "Restaurants non triés"
            )
        }
    }
}
