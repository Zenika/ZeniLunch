package com.zenika.zenilunch.agency

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.zenika.zenilunch.agency.component.AgenciesList
import com.zenika.zenilunch.agency.model.Agency

@Composable
fun AgencySelectionScreen(
    viewModel: AgencySelectionViewModel = hiltViewModel(),
    onExit: () -> Unit
) {
    val state by viewModel.state.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.init()
    }

    LaunchedEffect(Unit) {
        viewModel.leave.collect { leave ->
            if (leave) {
                onExit()
            }
        }
    }

    AgencySelectionContent(
        state,
        modifier = Modifier.fillMaxSize(),
        onAgencySelect = viewModel::onAgencySelect
    )
}

@Composable
fun AgencySelectionContent(
    state: AgencySelectionUiState,
    modifier: Modifier,
    onAgencySelect: (Agency) -> Unit
) {
    when (state) {
        AgencySelectionUiState.Loading -> Box(modifier) {
            CircularProgressIndicator(
                Modifier
                    .size(64.dp)
                    .align(Alignment.Center)
            )
        }
        is AgencySelectionUiState.AgenciesList -> AgenciesList(
            agencies = state.agencies,
            onAgencyClick = onAgencySelect,
            modifier = modifier
        )
    }
}
