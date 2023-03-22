package com.zenika.zenilunch

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentContainerView
import androidx.fragment.app.commit

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun onRestaurantClick(restaurant: RestaurantUIModel) {
        val detailFragment = findViewById<FragmentContainerView>(R.id.detailfragment)
        if (detailFragment == null) {
            supportFragmentManager.commit {
                replace(R.id.fragmentHolder, DetailFragment.newInstance(restaurant))
                addToBackStack(null)
            }
        } else {
            supportFragmentManager.commit {
                replace(R.id.detailfragment, DetailFragment.newInstance(restaurant))
            }
        }
    }
}