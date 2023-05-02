package com.zenika.zenilunch.ageny

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.zenika.zenilunch.ageny.component.AgenciesList

@Composable
fun AgencySelectionScreen(
    viewModel: AgencySelectionViewModel = hiltViewModel(),
    onAgencySelection: () -> Unit
) {
    val state by viewModel.state.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.init()
    }

    AgenciesList(
        agencies = state.agencies,
        onAgencyClick = {
            onAgencySelection()
        },
        modifier = Modifier
            .fillMaxSize()
    )
}
