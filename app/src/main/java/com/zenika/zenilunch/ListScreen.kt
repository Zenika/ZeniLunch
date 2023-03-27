package com.zenika.zenilunch

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun ListScreen(onRestaurantClick: (restaurant: RestaurantUIModel) -> Unit) {
    val restaurants = createRestaurants()
    LazyColumn {
        item {
            Text(text = "Retrouve tous les reZtos proches de ton agence Zenika")
        }
        items(restaurants.size) { index ->
            val restaurant = restaurants[index]
            val restaurantName = restaurant.name
            Card(
                Modifier
                    .fillMaxWidth()
                    .padding(10.dp)
                    .clickable {
                        onRestaurantClick(restaurant)
                    }
            ) {
                Text(text = restaurantName, Modifier
                    .padding(24.dp))
            }
        }
    }
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