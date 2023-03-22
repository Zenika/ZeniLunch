package com.zenika.zenilunch

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.launch

class ListFragment : Fragment() {

    private val viewModel: ListViewModel by viewModels()
    private lateinit var adapter: RestaurantAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_list, container, false)
        adapter = RestaurantAdapter { restaurant ->
            (activity as? MainActivity)?.onRestaurantClick(restaurant)
        }
        view.findViewById<RecyclerView>(R.id.recyclerview).apply {
            this.layoutManager = LinearLayoutManager(context)
            this.adapter = this@ListFragment.adapter
        }
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        lifecycleScope.launch {
            viewModel.restaurants.collect { restaurants ->
                adapter.submitList(restaurants)
            }
        }
    }

    override fun onResume() {
        super.onResume()
        viewModel.init()
    }
}