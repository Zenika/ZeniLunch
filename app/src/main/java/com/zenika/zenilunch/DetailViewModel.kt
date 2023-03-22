package com.zenika.zenilunch

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class DetailViewModel : ViewModel() {
    private val _restaurant = MutableStateFlow(RestaurantUIModel("", "", "", vegetarian = false, vegan = false, 0.0, 0.0))
    var restaurant: StateFlow<RestaurantUIModel> = _restaurant.asStateFlow()

    fun setRestaurant(restaurant: RestaurantUIModel) {
        _restaurant.update {
            restaurant
        }
    }
}