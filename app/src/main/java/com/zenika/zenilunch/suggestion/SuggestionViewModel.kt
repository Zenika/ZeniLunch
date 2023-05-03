package com.zenika.zenilunch.suggestion

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zenika.zenilunch.RestaurantUIModel
import com.zenika.zenilunch.mapper.convertRestaurantObject
import com.zenika.zenilunch.repository.RestaurantRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class SuggestionViewModel @Inject constructor(
    private val restaurantRepository: RestaurantRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    private var restaurantName: String =
        savedStateHandle.get<String>("name") ?: error("Restaurant name is required!")

    val restaurant: StateFlow<RestaurantUIModel> = flow {
        val restaurants = getRestaurant()
        emit(restaurants)
    }.stateIn(
        viewModelScope,
        started = SharingStarted.WhileSubscribed(stopTimeoutMillis = 5_000),
        initialValue = RestaurantUIModel("", "", "", vegetarian = false, vegan = false, .0, .0)
    )

    private suspend fun getRestaurant(): RestaurantUIModel {
        val restaurants = restaurantRepository.getRestaurants()
        val restaurant = restaurants.first { restaurant -> restaurant.name == restaurantName }
        return restaurant.convertRestaurantObject()
    }
}
