package com.zenika.zenilunch

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class ListFragment : Fragment() {

    companion object {
        fun newInstance(): ListFragment {
            return ListFragment()
        }
    }

    private lateinit var viewModel: ListViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_list, container, false)
        val adapter = RestaurantAdapter { restaurant ->
            val intent = DetailFragment.getStartIntent(activity)
            startActivity(intent)
        }

        view.findViewById<RecyclerView>(R.id.recyclerview).apply {
            this.layoutManager = LinearLayoutManager(context)
            this.adapter = adapter
        }

        val restaurants = createRestaurants()
        adapter.submitList(restaurants)
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this)[ListViewModel::class.java]

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