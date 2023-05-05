package com.zenika.zenilunch.list

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.style.TextAlign
import com.zenika.zenilunch.RestaurantUIModel
import com.zenika.zenilunch.ui.theme.cardPadding

@Composable
fun RestaurantCard(
    goToDetailScreen: (restaurant: RestaurantUIModel) -> Unit,
    restaurant: RestaurantUIModel,
    modifier: Modifier = Modifier
) {
    Card(
        modifier
            .fillMaxWidth()
            .semantics(mergeDescendants = true) {}
    ) {
        Text(
            text = restaurant.name, Modifier
                .fillMaxSize()
                .clickable(onClickLabel = "Ouvrir le détail") {
                    goToDetailScreen(restaurant)
                }
                .padding(cardPadding),
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.headlineSmall
        )
    }
}
