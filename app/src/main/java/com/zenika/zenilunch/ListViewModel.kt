package com.zenika.zenilunch

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class ListViewModel : ViewModel() {
    private val _restaurants = MutableStateFlow<List<RestaurantUIModel>>(emptyList())
    val restaurants: StateFlow<List<RestaurantUIModel>> = _restaurants.asStateFlow()

    fun init() {
        _restaurants.update { createRestaurants() }
    }

    private fun createRestaurants(): List<RestaurantUIModel> {
        return listOf(
            RestaurantUIModel("Kaffee Berlin", "Burger", "€", vegetarian = true, vegan = true, 45.767551889608235, 4.857335592897319),
            RestaurantUIModel("Happy Feel", "Végétarien", "€", vegetarian = true, vegan = true, 45.76864100678723, 4.8619654828776016),
            RestaurantUIModel("Chez Jules", "Boulangerie", "€", vegetarian = false, vegan = false, 45.76648502742043, 4.856709398222179),
            RestaurantUIModel("O Pad Thaï", "Thaïlandais", "€", vegetarian = false, vegan = false, 45.76399480415859, 4.856358936709006),
            RestaurantUIModel("Jojo Pizza", "Pizzeria", "€", vegetarian = true, vegan = false, 45.77004280935307, 4.858317698222286)
        )
    }
}