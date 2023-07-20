package com.zenika.zenilunch.repository

import com.zenika.zenilunch.agency.model.Agency
import com.zenika.zenilunch.agency.model.LatLng
import com.zenika.zenilunch.data.HiddenRestaurant
import com.zenika.zenilunch.data.RestaurantDao
import com.zenika.zenilunch.network.RestaurantDto
import com.zenika.zenilunch.network.RestaurantNetwork
import io.mockk.coEvery
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import java.util.Date

@ExtendWith(MockKExtension::class)
class RestaurantRepositoryTest {

    @MockK
    lateinit var restaurantDao: RestaurantDao

    @MockK
    lateinit var restaurantNetwork: RestaurantNetwork

    @InjectMockKs
    lateinit var repository: RestaurantRepository

    @Test
    @DisplayName("Should not return any hidden restaurant")
    fun shouldNotReturnAnyHiddenRestaurant() = runTest {
        coEvery { restaurantDao.getHiddenRestaurants() } returns
            listOf(HiddenRestaurant("Pas bon", Date()))
        coEvery { restaurantNetwork.getRestaurants(any()) } returns
            listOf(
                RestaurantDto("Pas bon", "Pâtes", "##€", false, false, 0.0, 0.0),
                RestaurantDto("Super bon", "Pizza", "€", true, true, 0.0, 0.0),
            )

        val restaurants =
            repository.getUnhiddenRestaurants(Agency("lyon", "Lyon", "", "", LatLng(0.0, 0.0)))

        assertEquals(
            1,
            restaurants.size
        )
        assertEquals(
            "Super bon",
            restaurants[0].name
        )
    }
}
