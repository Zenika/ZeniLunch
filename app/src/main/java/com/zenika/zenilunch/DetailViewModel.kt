package com.zenika.zenilunch

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class DetailViewModel : ViewModel() {
    private val _restaurant = MutableStateFlow(RestaurantUIModel("Kaffee Berlin", "Burger", "€", vegetarian = true, vegan = true, 45.767551889608235, 4.857335592897319))
    var restaurant: StateFlow<RestaurantUIModel> = _restaurant.asStateFlow()

    fun setRestaurant(restaurant: RestaurantUIModel) {
        _restaurant.update {
            restaurant
        }
    }
}