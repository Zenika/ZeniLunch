package com.zenika.zenilunch

import android.content.Intent
import android.net.Uri
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel

class DetailViewModel(
    savedStateHandle: SavedStateHandle
): ViewModel() {
    private var restaurantName :String = savedStateHandle.get<String>("name") ?: error("Restaurant name is required!")

    fun setRestaurantName(name: String) {
        restaurantName = name
    }

    fun getRestaurant(): RestaurantUIModel {
        val restaurants = listOf(
            RestaurantUIModel("Kaffee Berlin", "Burger", "€", vegetarian = true, vegan = true, 45.767551889608235, 4.857335592897319),
            RestaurantUIModel("Happy Feel", "Végétarien", "€", vegetarian = true, vegan = true, 45.76864100678723, 4.8619654828776016),
            RestaurantUIModel("Chez Jules", "Boulangerie", "€", vegetarian = false, vegan = false, 45.76648502742043, 4.856709398222179),
            RestaurantUIModel("O Pad Thaï", "Thaïlandais", "€", vegetarian = false, vegan = false, 45.76399480415859, 4.856358936709006),
            RestaurantUIModel("Jojo Pizza", "Pizzeria", "€", vegetarian = true, vegan = false, 45.77004280935307, 4.858317698222286)
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

    fun openGoogleMaps() {
        val restaurant = getRestaurant()
        val officeLatitude = 45.766752337134754
        val officeLongitude = 4.858952442403011
        val latitude = restaurant.latitude
        val longitude = restaurant.longitude
        val url =
            "http://maps.google.com/maps?saddr=$officeLatitude,$officeLongitude&daddr=$latitude,$longitude"
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        intent.setPackage("com.google.android.apps.maps")
    }
}