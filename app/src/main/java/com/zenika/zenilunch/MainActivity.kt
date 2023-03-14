package com.zenika.zenilunch

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val adapter = RestaurantAdapter { restaurant ->
            val intent = Intent(this, DetailActivity::class.java)
            intent.putExtra("restaurant", restaurant)
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
            RestaurantUIModel("Kaffee Berlin", "Burger", "€", vegetarian = true, vegan = true),
            RestaurantUIModel("Happy Feel", "Végétarien", "€", vegetarian = true, vegan = true),
            RestaurantUIModel("Chez Jules", "Boulangerie", "€", vegetarian = false, vegan = false),
            RestaurantUIModel("O Pad Thaï", "Thaïlandais", "€", vegetarian = false, vegan = false),
            RestaurantUIModel("Jojo Pizza", "Pizzeria", "€", vegetarian = true, vegan = false)
        )
    }
}