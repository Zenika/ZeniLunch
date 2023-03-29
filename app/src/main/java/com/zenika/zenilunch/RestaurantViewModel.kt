package com.zenika.zenilunch

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.stateIn

class RestaurantViewModel(
    savedStateHandle: SavedStateHandle
): ViewModel() {
    private var restaurantName: String = savedStateHandle.get<String>("name") ?: error("Restaurant name is required!")

    val restaurant: StateFlow<RestaurantUIModel> = flowOf(
        getRestaurant()
    ).stateIn(
        viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = RestaurantUIModel("", "", "", vegetarian = false, vegan = false, .0, .0)
    )

    private fun getRestaurant(): RestaurantUIModel {
        val restaurants = listOf(
            RestaurantUIModel("Kaffee Berlin", "Burger", "€€", vegetarian = true, vegan = true, 45.767551889608235, 4.857335592897319),
            RestaurantUIModel("Happy Feel", "Végétarien", "€€", vegetarian = true, vegan = true, 45.76864100678723, 4.8619654828776016),
            RestaurantUIModel("Chez Jules", "Boulangerie", "€", vegetarian = false, vegan = false, 45.76648502742043, 4.856709398222179),
            RestaurantUIModel("O Pad Thaï", "Thaïlandais", "€€", vegetarian = false, vegan = false, 45.76399480415859, 4.856358936709006),
            RestaurantUIModel("Jojo Pizza", "Pizzeria", "€", vegetarian = true, vegan = false, 45.77004280935307, 4.858317698222286),
            RestaurantUIModel("Ok Sushi", "Japonais", "€€", true, false, 45.76855096798334, 4.8494913730169795),
            RestaurantUIModel("Chikin Bang", "Coréen", "€", false, false, 45.761726612268745, 4.856067793275381),
            RestaurantUIModel("Manger Vite & Bien", "Bar à salades", "€", true, true, 45.764033379006165, 4.857152298222151),
            RestaurantUIModel("Subway", "Sandwich", "€€", true, true, 45.76959922404694, 4.854517083590193)
        )

        var restaurantValues = RestaurantUIModel("Error", "", "", vegetarian = false, vegan = false, 0.0, 0.0)
        for (restaurant in restaurants) {
            if (restaurant.name == restaurantName) {
                restaurantValues = restaurant
                break
            }
        }
        return restaurantValues
    }
}