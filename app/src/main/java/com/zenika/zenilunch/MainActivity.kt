package com.zenika.zenilunch

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val adapter = RestaurantAdapter { restaurant ->
            val intent = DetailActivity.getStartIntent(this, restaurant)
            startActivity(intent)
        }

        findViewById<RecyclerView>(R.id.recyclerview).apply {
            this.layoutManager = LinearLayoutManager(this@MainActivity)
            this.adapter = adapter
        }

        val restaurants = createRestaurants()
        adapter.submitList(restaurants)
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