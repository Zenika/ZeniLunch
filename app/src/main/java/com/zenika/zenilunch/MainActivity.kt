package com.zenika.zenilunch

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val adapter = RestaurantAdapter { restaurant ->
            Toast.makeText(this, restaurant.name, Toast.LENGTH_SHORT).show()
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
            RestaurantUIModel("Kaffee Berlin", "Burger", "€"),
            RestaurantUIModel("Happy Feel", "Végétarien", "€"),
            RestaurantUIModel("Chez Jules", "Boulangerie", "€"),
            RestaurantUIModel("O Pad Thaï", "Thaïlandais", "€"),
            RestaurantUIModel("Jojo Pizza", "Pizzeria", "€")
        )
    }
}