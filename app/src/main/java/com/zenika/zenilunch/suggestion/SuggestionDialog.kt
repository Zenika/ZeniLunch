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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.hilt.navigation.compose.hiltViewModel
import com.zenika.zenilunch.RestaurantUIModel
import com.zenika.zenilunch.ui.theme.PreviewZeniLunchTheme
import com.zenika.zenilunch.ui.theme.dialogPadding
import com.zenika.zenilunch.ui.theme.screenPadding
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.toImmutableList

@Composable
fun SuggestionDialog(
    onDismissRequest: () -> Unit,
    viewModel: SuggestionViewModel = hiltViewModel()
) {
    val restaurants by viewModel.restaurants.collectAsState()
    Dialog(
        onDismissRequest = onDismissRequest,
        properties = DialogProperties(dismissOnBackPress = true, dismissOnClickOutside = true)
    ) {
        SuggestionsContent(restaurants)
    }
}

@Composable
private fun SuggestionsContent(
    restaurants: ImmutableList<RestaurantUIModel>,
) {
    Column {
        restaurants.forEach { restaurant ->
            SuggestionContent(
                restaurant
            )
        }
    }
}

@Composable
private fun SuggestionContent(
    restaurant: RestaurantUIModel
) {
    Column(
        Modifier
            .fillMaxWidth()
            .padding(dialogPadding)
            .background(
                MaterialTheme.colorScheme.inversePrimary,
                MaterialTheme.shapes.extraLarge
            )
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

@Preview
@Composable
private fun SuggestionsContentPreview() {
    PreviewZeniLunchTheme {
        SuggestionsContent(
            restaurants = listOf(
                RestaurantUIModel(
                    "Chez Audrey",
                    "Dev Android",
                    "€€€",
                    vegetarian = true,
                    vegan = true,
                    latitude = 1.0,
                    longitude = 2.0
                ),
                RestaurantUIModel(
                    "Chez Loïc",
                    "Dev Backend",
                    "€€€",
                    vegetarian = true,
                    vegan = true,
                    latitude = 1.0,
                    longitude = 2.0
                ),
                RestaurantUIModel(
                    "Chez Olivier",
                    "Dev",
                    "€",
                    vegetarian = false,
                    vegan = false,
                    latitude = 1.0,
                    longitude = 2.0
                )
            ).toImmutableList()
        )
    }
}
