package com.zenika.zenilunch.detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zenika.zenilunch.RestaurantUIModel
import com.zenika.zenilunch.domain.GetRestaurantByNameUseCase
import com.zenika.zenilunch.domain.HideRestaurantUseCase
import com.zenika.zenilunch.mapper.convertRestaurantObject
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val getRestaurantByName: GetRestaurantByNameUseCase,
    private val hideRestaurant: HideRestaurantUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    private var restaurantName: String =
        savedStateHandle.get<String>("name") ?: error("Restaurant name is required!")

    val restaurant: StateFlow<RestaurantUIModel> = flow {
        val restaurants = getRestaurant()
        emit(restaurants)
    }.stateIn(
        viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = RestaurantUIModel("", "", "", vegetarian = false, vegan = false, .0, .0)
    )

    private suspend fun getRestaurant(): RestaurantUIModel {
        val restaurant = getRestaurantByName(restaurantName)
        return restaurant.convertRestaurantObject()
    }

    fun hideRestaurant() {
        viewModelScope.launch {
            hideRestaurant(restaurantName)
        }
    }
}

