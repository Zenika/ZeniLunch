package com.zenika.zenilunch.suggestion

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.fadeIn
import androidx.compose.animation.scaleIn
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.zenika.zenilunch.RestaurantUIModel
import com.zenika.zenilunch.ui.component.AutoResizeDialog
import com.zenika.zenilunch.ui.theme.PreviewZeniLunchTheme
import com.zenika.zenilunch.ui.theme.screenPadding
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.toImmutableList

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun SuggestionDialog(
    onDismissRequest: () -> Unit,
    viewModel: SuggestionViewModel = hiltViewModel()
) {
    val restaurants by viewModel.restaurants.collectAsState()
    AutoResizeDialog(
        onDismissRequest = onDismissRequest,
    ) {
        AnimatedVisibility(
            visible = restaurants.isNotEmpty(),
            label = "suggestions-animation",
            enter = scaleIn(initialScale = .9f) + fadeIn()
        ) {
            SuggestionsContent(restaurants)
        }
    }
}

@Composable
private fun SuggestionsContent(
    restaurants: ImmutableList<RestaurantUIModel>,
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
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
