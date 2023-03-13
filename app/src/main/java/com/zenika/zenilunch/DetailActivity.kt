package com.zenika.zenilunch

import android.os.Build
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class DetailActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.detail_activity)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val bundleRestaurant = intent.extras
        var restaurant: RestaurantUIModel = RestaurantUIModel("Error", "Error", "Error", 0.0, 0.0)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if (bundleRestaurant != null) {
                restaurant =
                    bundleRestaurant.getSerializable("restaurant", RestaurantUIModel::class.java)!!
            }
        }

        getInfo(restaurant)
    }

    private fun getInfo(restaurant: RestaurantUIModel) {
        val name = findViewById<TextView>(R.id.name)
        val type = findViewById<TextView>(R.id.type)
        val price = findViewById<TextView>(R.id.price)
        name.text = restaurant.name
        type.text = restaurant.type
        price.text = restaurant.price
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }
}