package com.zenika.zenilunch

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun DetailScreen(viewModel: DetailViewModel = hiltViewModel()) {
    viewModel.setRestaurantName("Kaffee Berlin")
    val restaurant = viewModel.getRestaurant()
    Column(
        Modifier
            .fillMaxWidth()
            .fillMaxHeight(),
        horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = restaurant.name, modifier,
            textAlign = TextAlign.Center,
            fontSize = 24.sp)
        Text(text = restaurant.type, modifier,
            textAlign = TextAlign.Center,
            fontSize = 20.sp)
        Text(text = restaurant.price, modifier,
            textAlign = TextAlign.Center,
            fontSize = 20.sp)
        Text(text = "Option", modifier,
            textAlign = TextAlign.Center,
            fontSize = 18.sp)
        Button(onClick = { viewModel.openGoogleMaps() }) {
            Text(text = "Ouvrir Google Maps")
        }
    }
}

@androidx.compose.ui.tooling.preview.Preview
@Composable
fun DetailScreenPreview() {
    DetailScreen()
}