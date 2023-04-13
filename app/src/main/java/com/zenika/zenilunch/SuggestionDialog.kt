package com.zenika.zenilunch

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.hilt.navigation.compose.hiltViewModel
import com.zenika.zenilunch.ui.theme.screenPadding

@Composable
fun SuggestionDialog(
    openDialog: MutableState<Boolean>,
    viewModel: RestaurantViewModel = hiltViewModel()
) {
    val restaurant by viewModel.restaurant.collectAsState()
    Dialog(
        onDismissRequest = { openDialog.value = false },
        properties = DialogProperties(dismissOnBackPress = true, dismissOnClickOutside = true)
    ) {
        Column(
            Modifier
                .fillMaxWidth()
                .padding(screenPadding)
                .background(
                    MaterialTheme.colorScheme.inversePrimary,
                    MaterialTheme.shapes.extraLarge
                ),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = restaurant.name,
                Modifier.padding(screenPadding),
                style = MaterialTheme.typography.headlineMedium
            )
            Text(
                text = restaurant.type,
                Modifier.padding(screenPadding),
                style = MaterialTheme.typography.titleLarge
            )
        }
    }
}
