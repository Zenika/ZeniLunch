package com.zenika.zenilunch

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModelProvider

class DetailFragment : Fragment() {
    companion object {
        fun getStartIntent(context: FragmentActivity?) : Intent {
            val intent = Intent(context, DetailFragment::class.java)
            return intent
        }
    }

    private lateinit var viewModel: DetailViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        /*intent.extras
            ?.getParcelable<RestaurantUIModel>("restaurant")
            ?.let { restaurant ->
                displayDetails(restaurant)
                view.findViewById<Button>(R.id.map).setOnClickListener {
                    openGoogleMaps(restaurant)
                }
            }
            ?: error("This activity requires a restaurant!")

         */
        return inflater.inflate(R.layout.fragment_detail, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this)[DetailViewModel::class.java]
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