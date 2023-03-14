package com.zenika.zenilunch

import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity

class DetailActivity : AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.detail_activity)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val bundleRestaurant = intent.extras
        val restaurant: RestaurantUIModel

        if (bundleRestaurant != null) {
            restaurant = bundleRestaurant.getParcelable("restaurant", RestaurantUIModel::class.java)!!
            getInfo(restaurant)
        } else {
            Log.e("DetailActivity", "RestaurantUIModel object error")
        }
    }

    private fun getInfo(restaurant: RestaurantUIModel) {
        val name = findViewById<TextView>(R.id.name)
        val type = findViewById<TextView>(R.id.type)
        val price = findViewById<TextView>(R.id.price)
        val option = findViewById<TextView>(R.id.option)
        name.text = restaurant.name
        type.text = restaurant.type
        price.text = restaurant.price

        option.text = ""
        if (restaurant.vegetarian && restaurant.vegan) {
            option.text = "Options : Végétarien & Vegan"
        } else if (restaurant.vegetarian) option.text = "Option : Végétarien"
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }
}