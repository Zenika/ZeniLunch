package com.zenika.zenilunch.agency

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zenika.zenilunch.agency.model.Agency
import com.zenika.zenilunch.domain.GetAllAgenciesUseCase
import com.zenika.zenilunch.domain.HasSelectedAgencyUseCase
import com.zenika.zenilunch.domain.SelectAgencyUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.toImmutableList
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class AgencySelectionViewModel @Inject constructor(
    private val getAgencies: GetAllAgenciesUseCase,
    private val hasSelectedAgency: HasSelectedAgencyUseCase,
    private val selectAgency: SelectAgencyUseCase
) : ViewModel() {

    private val _state = MutableStateFlow<AgencySelectionUiState>(AgencySelectionUiState.Loading)
    val state = _state
        .stateIn(
            scope = viewModelScope,
            initialValue = AgencySelectionUiState.Loading,
            started = SharingStarted.WhileSubscribed(stopTimeoutMillis = 5_000),
        )

    private val _leave = MutableStateFlow(false)
    val leave = _leave.asStateFlow()

    fun init() {
        viewModelScope.launch {
            if (hasSelectedAgency()) {
                _leave.update { true }
                return@launch
            }

            val agencies = withContext(Dispatchers.Default) {
                AgencySelectionUiState.AgenciesList(getAgencies().toImmutableList())
            }
            _state.update { agencies }
        }
    }

    fun onAgencySelect(agency: Agency) {
        viewModelScope.launch {
            selectAgency(agency)
            _leave.update { true }
        }
    }
}

sealed interface AgencySelectionUiState {
    object Loading : AgencySelectionUiState
    data class AgenciesList(
        val agencies: ImmutableList<Agency>
    ) : AgencySelectionUiState
}
