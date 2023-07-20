package com.zenika.zenilunch

import com.zenika.zenilunch.agency.model.Agency
import com.zenika.zenilunch.agency.model.LatLng
import com.zenika.zenilunch.domain.GetSuggestionsUseCase
import com.zenika.zenilunch.network.RestaurantDto
import com.zenika.zenilunch.repository.AgencyRepository
import com.zenika.zenilunch.repository.RestaurantRepository
import io.mockk.coEvery
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.extension.ExtendWith
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource

@ExtendWith(MockKExtension::class)
internal class GetSuggestionsUseCaseTest {

    @MockK
    lateinit var agencyRepository: AgencyRepository

    @MockK
    lateinit var restaurantRepository: RestaurantRepository

    @InjectMockKs
    lateinit var getSuggestionsUseCase: GetSuggestionsUseCase

    private val chezLoic = RestaurantDto(
        "Chez Loïc",
        type = "Dev Loïc",
        price = "",
        vegetarian = true,
        vegan = false,
        latitude = .0,
        longitude = .0
    )

    @ParameterizedTest
    @MethodSource("givenRestaurantsSizeAndExpectedSuggestionsSize")
    @DisplayName("Should get maximum 3 suggestions")
    fun shouldGetMaximum3Suggestions(givenRestaurantsSize: Int, expectedSuggestionsSize: Int) {
        runTest {
            // Given
            coEvery { agencyRepository.getSelectedAgency() } returns Agency(
                "id",
                "name",
                "logoUrl",
                "path",
                LatLng(0.0, 0.0)
            )
            coEvery { restaurantRepository.getRestaurants(any()) } returns
                (0 until givenRestaurantsSize).map { chezLoic }

            // When
            val suggestions = getSuggestionsUseCase()

            // Then
            assertTrue(
                suggestions.size == expectedSuggestionsSize,
                "Mauvais nombre de suggestions"
            )
        }
    }

    companion object {
        @JvmStatic
        fun givenRestaurantsSizeAndExpectedSuggestionsSize() = listOf(
            Arguments.of(0, 0),
            Arguments.of(1, 1),
            Arguments.of(2, 2),
            Arguments.of(3, 3),
            Arguments.of(4, 3),
        )
    }
}
