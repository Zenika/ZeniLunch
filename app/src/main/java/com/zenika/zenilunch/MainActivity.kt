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
        val recyclerview = findViewById<RecyclerView>(R.id.recyclerview)

        recyclerview.layoutManager = LinearLayoutManager(this)

        val restaurants = createRestaurants()

        val adapter = RestaurantAdapter { restaurant ->
            val intent = Intent(this, DetailActivity::class.java)
            intent.putExtra("restaurant", restaurant)
            startActivity(intent)
        }
        recyclerview.adapter = adapter
        adapter.submitList(restaurants)
    }

    private fun createRestaurants(): List<RestaurantUIModel> {
        return listOf(
            RestaurantUIModel("Kaffee Berlin", "Burger", "€", 45.7675387233886, 4.857331561566509),
            RestaurantUIModel("Happy Feel", "Végétarien", "€", 45.76867596150166, 4.862050498276127),
            RestaurantUIModel("Chez Jules", "Boulangerie", "€", 45.76646066123867, 4.856761182098617),
            RestaurantUIModel("O Pad Thaï", "Thaï", "€", 45.76401820476476, 4.8563127718784305),
            RestaurantUIModel("Jojo Pizza", "Pizzeria", "€", 45.76986085759058, 4.858309342911937)
        )
    }
}