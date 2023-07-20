package com.zenika.zenilunch.suggestion

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zenika.zenilunch.RestaurantUIModel
import com.zenika.zenilunch.domain.GetSuggestionsUseCase
import com.zenika.zenilunch.mapper.convertRestaurantObject
import com.zenika.zenilunch.network.RestaurantDto
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf
import kotlinx.collections.immutable.toImmutableList
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class SuggestionViewModel @Inject constructor(
    private val getSuggestions: GetSuggestionsUseCase,
) : ViewModel() {
    val restaurants: StateFlow<ImmutableList<RestaurantUIModel>> = flow {
        emit(getRestaurants())
    }.stateIn(
        viewModelScope,
        started = SharingStarted.WhileSubscribed(stopTimeoutMillis = 5_000),
        initialValue = persistentListOf()
    )

    private suspend fun getRestaurants(): ImmutableList<RestaurantUIModel> {
        return getSuggestions()
            .map(RestaurantDto::convertRestaurantObject)
            .toImmutableList()
    }
}
