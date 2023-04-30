package com.zenika.zenilunch.list

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zenika.zenilunch.RestaurantUIModel
import com.zenika.zenilunch.mapper.convertRestaurantObject
import com.zenika.zenilunch.repository.RestaurantRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class ListViewModel @Inject constructor(
    private val restaurantRepository: RestaurantRepository
) : ViewModel() {
    val restaurants: StateFlow<List<RestaurantUIModel>> = flow {
        val restaurants = getRestaurants()
        emit(restaurants)
    }.stateIn(
        viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = listOf(
            RestaurantUIModel(
                "",
                "",
                "",
                vegetarian = false,
                vegan = false,
                .0,
                .0
            )
        )
    )

    private suspend fun getRestaurants(): List<RestaurantUIModel> {
        val restaurants = restaurantRepository.getRestaurants()
        Log.d("restaurants", restaurants.toString())
        return restaurants.map { restaurant ->
            restaurant.convertRestaurantObject()
        }
    }
}
