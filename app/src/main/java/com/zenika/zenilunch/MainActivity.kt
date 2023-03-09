package com.zenika.zenilunch

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
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
            intent.putExtra("name", restaurant.name)
            startActivity(intent)
        }
        recyclerview.adapter = adapter
        adapter.submitList(restaurants)
    }

    private fun createRestaurants(): ArrayList<RestaurantUIModel> {
        val restaurants = ArrayList<RestaurantUIModel>()
        val restaurantsList = arrayOf("Kaffee Berlin", "Happy feel", "Ogam", "Chez Jules", "O Pad Thaï")

        for (restaurantList in restaurantsList) {
            val restaurant = RestaurantUIModel(restaurantList)
            restaurants.add(restaurant)
        }
        return restaurants
    }
}