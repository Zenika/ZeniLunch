package com.zenika.zenilunch.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zenika.zenilunch.RestaurantUIModel
import com.zenika.zenilunch.agency.model.Agency
import com.zenika.zenilunch.data.HiddenRestaurant
import com.zenika.zenilunch.domain.GetRestaurantsSortedByNameUseCase
import com.zenika.zenilunch.domain.GetSelectedAgencyUseCase
import com.zenika.zenilunch.mapper.convertRestaurantObject
import com.zenika.zenilunch.data.network.RestaurantDto
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.toImmutableList
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class ListViewModel @Inject constructor(
    private val getRestaurantSortedByName: GetRestaurantsSortedByNameUseCase,
    private val getSelectedAgency: GetSelectedAgencyUseCase
) : ViewModel() {

    private val _state = MutableStateFlow<ListUiState>(ListUiState.Loading)
    val state: StateFlow<ListUiState> = _state
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(stopTimeoutMillis = 5_000),
            initialValue = ListUiState.Loading
        )

    fun init() {
        viewModelScope.launch {
            val selectedAgency = getSelectedAgency()
            val newRestaurants = withContext(Dispatchers.Default) {
                getRestaurants().toImmutableList()
            }

            _state.update {
                ListUiState.Loaded(selectedAgency, newRestaurants)
            }
        }
    }

    private suspend fun getRestaurants(): List<RestaurantUIModel> {
        val restaurants = getRestaurantSortedByName()
        return restaurants.map { restaurant -> restaurant.convertRestaurantObject() }
    }
}

private operator fun List<HiddenRestaurant>.contains(restaurant: RestaurantDto): Boolean {
    return this.any { it.name == restaurant.name }
}

sealed interface ListUiState {
    object Loading : ListUiState
    data class Loaded(
        val agency: Agency,
        val restaurants: ImmutableList<RestaurantUIModel>
    ) : ListUiState
}
