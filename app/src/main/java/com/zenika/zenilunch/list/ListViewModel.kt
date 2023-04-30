package com.zenika.zenilunch.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zenika.zenilunch.RestaurantUIModel
import com.zenika.zenilunch.data.HiddenRestaurant
import com.zenika.zenilunch.domain.GetRestaurantsSortedByNameUseCase
import com.zenika.zenilunch.mapper.convertRestaurantObject
import com.zenika.zenilunch.network.RestaurantDto
import com.zenika.zenilunch.repository.RestaurantRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ListViewModel @Inject constructor(
    private val getRestaurantSortedByName: GetRestaurantsSortedByNameUseCase,
    private val restaurantRepository: RestaurantRepository
) : ViewModel() {

    private val _restaurants = MutableStateFlow(listOf<RestaurantUIModel>())
    val restaurants: StateFlow<List<RestaurantUIModel>> = _restaurants
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(stopTimeoutMillis = 5_000),
            initialValue = listOf(
                RestaurantUIModel(
                    "",
                    "",
                    "",
                    vegetarian = false,
                    vegan = false,
                    latitude = .0,
                    longitude = .0
                )
            )
        )

    fun init() {
        viewModelScope.launch {
            val newRestaurants = getRestaurants()
            _restaurants.update { newRestaurants }
        }
    }

    private suspend fun getRestaurants(): List<RestaurantUIModel> {
        val hiddenRestaurants = restaurantRepository.getHiddenRestaurants()
        val restaurants = getRestaurantSortedByName()
        return restaurants
            .filterNot { it in hiddenRestaurants }
            .map { restaurant -> restaurant.convertRestaurantObject() }
    }
}

private operator fun List<HiddenRestaurant>.contains(restaurant: RestaurantDto): Boolean {
    return this.any { it.name == restaurant.name }
}
