package com.zenika.zenilunch

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch

class DetailFragment : Fragment() {

    companion object {
        fun newInstance(restaurant: RestaurantUIModel): Fragment {
            return DetailFragment().apply {
                arguments = bundleOf(
                    "restaurant" to restaurant
                )
            }
        }
    }

    private val viewModel: DetailViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_detail, container, false)
        val restaurant = arguments?.getParcelable<RestaurantUIModel>("restaurant")

        if (restaurant != null) {
            lifecycleScope.launch {
                viewModel.setRestaurant(restaurant)
            }
        } else {
            Toast.makeText(context, "Restaurant not found", Toast.LENGTH_SHORT).show()
        }
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        lifecycleScope.launch {
            viewModel.restaurant.collect { restaurant ->
                displayDetails(restaurant)
                val map = requireView().findViewById<Button>(R.id.map)
                map.setOnClickListener() {
                    openGoogleMaps(restaurant)
                }
            }
        }
    }

    override fun onResume() {
        super.onResume()
    }

    private fun displayDetails(restaurant: RestaurantUIModel) {
        val name = requireView().findViewById<TextView>(R.id.name)
        val type = requireView().findViewById<TextView>(R.id.type)
        val price = requireView().findViewById<TextView>(R.id.price)
        val option = requireView().findViewById<TextView>(R.id.option)
        name.text = restaurant.name
        type.text = restaurant.type
        price.text = restaurant.price

        option.text = when {
            restaurant.vegan -> getString(R.string.option, getString(R.string.vegan))
            restaurant.vegetarian -> getString(R.string.option, getString(R.string.vegetarian))
            else -> getString(R.string.noOption)
        }
    }

    private fun openGoogleMaps(restaurant: RestaurantUIModel) {
        val officeLatitude = 45.766752337134754
        val officeLongitude = 4.858952442403011
        val latitude = restaurant.latitude
        val longitude = restaurant.longitude
        val url =
            "http://maps.google.com/maps?saddr=$officeLatitude,$officeLongitude&daddr=$latitude,$longitude"
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        intent.setPackage("com.google.android.apps.maps")
        startActivity(intent)
    }
}