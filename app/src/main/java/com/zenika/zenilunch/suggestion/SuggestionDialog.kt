package com.zenika.zenilunch.suggestion

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.hilt.navigation.compose.hiltViewModel
import com.zenika.zenilunch.RestaurantUIModel
import com.zenika.zenilunch.ui.theme.dialogPadding
import com.zenika.zenilunch.ui.theme.screenPadding

@Composable
fun SuggestionDialog(
    viewModel: SuggestionViewModel = hiltViewModel(),
    onDismissRequest: () -> Unit
) {
    val restaurant by viewModel.restaurant.collectAsState()
    Dialog(
        onDismissRequest = onDismissRequest,
        properties = DialogProperties(dismissOnBackPress = true, dismissOnClickOutside = true)
    ) {
        SuggestionContent(
            restaurant,
            Modifier
                .fillMaxWidth()
                .padding(dialogPadding)
                .background(
                    MaterialTheme.colorScheme.inversePrimary,
                    MaterialTheme.shapes.extraLarge
                )
        )
    }
}

@Composable
private fun SuggestionContent(
    restaurant: RestaurantUIModel,
    modifier: Modifier = Modifier
) {
    Column(
        modifier
            .padding(screenPadding),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = restaurant.name,
            style = MaterialTheme.typography.titleLarge,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
        Text(
            text = restaurant.type,
            style = MaterialTheme.typography.labelLarge,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
    }
}
