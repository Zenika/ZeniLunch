package com.zenika.zenilunch.ageny

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zenika.zenilunch.ageny.model.Agency
import com.zenika.zenilunch.domain.GetAgenciesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf
import kotlinx.collections.immutable.toImmutableList
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class AgencySelectionViewModel @Inject constructor(
    private val getAgencies: GetAgenciesUseCase
) : ViewModel() {

    private val _state = MutableStateFlow<ImmutableList<Agency>>(persistentListOf())
    val state = _state.asStateFlow()
        .map { AgencySelectionUiState(it) }
        .stateIn(
            scope = viewModelScope,
            initialValue = AgencySelectionUiState(persistentListOf()),
            started = SharingStarted.WhileSubscribed(stopTimeoutMillis = 5_000),
        )

    fun init() {
        viewModelScope.launch {
            val agencies = withContext(Dispatchers.Default) { getAgencies().toImmutableList() }
            _state.update { agencies }
        }
    }
}

data class AgencySelectionUiState(
    val agencies: ImmutableList<Agency>
)
