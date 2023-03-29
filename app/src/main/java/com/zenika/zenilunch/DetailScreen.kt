package com.zenika.zenilunch

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.zenika.zenilunch.ui.theme.PreviewZeniLunchTheme

@Composable
fun DetailScreen(
    viewModel: RestaurantViewModel = hiltViewModel()
) {
    val restaurant by viewModel.restaurant.collectAsState()
    Restaurant(
        restaurant, Modifier
            .fillMaxSize()
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun Restaurant(
    restaurant: RestaurantUIModel,
    modifier: Modifier
) {
    val context = LocalContext.current
    val option = when {
        restaurant.vegan -> stringResource(R.string.option, stringResource(R.string.vegan))
        restaurant.vegetarian -> stringResource(
            R.string.option,
            stringResource(R.string.vegetarian)
        )
        else -> stringResource(R.string.noOption)
    }
    Column(
        modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        TopAppBar(title = { Text(text = "ZeniLunch") })
        Text(
            text = restaurant.name,
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(73,93,146,255))
                .padding(0.dp, 16.dp),
            textAlign = TextAlign.Center,
            fontSize = 24.sp,
            color = Color.White

        )
        Text(
            text = restaurant.type,
            modifier = Modifier
                .fillMaxWidth(),
            textAlign = TextAlign.Center,
            fontSize = 20.sp
        )
        Text(
            text = restaurant.price,
            modifier = Modifier
                .fillMaxWidth(),
            textAlign = TextAlign.Center,
            fontSize = 20.sp
        )
        Text(
            text = option,
            modifier = Modifier
                .fillMaxWidth(),
            textAlign = TextAlign.Center,
            fontSize = 18.sp
        )
        Button(onClick = { context.openGoogleMaps(restaurant) }) {
            Text(text = "Ouvrir Google Maps")
        }
    }
}

fun Context.openGoogleMaps(restaurant: RestaurantUIModel) {
    val officeLatitude = 45.766752337134754
    val officeLongitude = 4.858952442403011
    val latitude = restaurant.latitude
    val longitude = restaurant.longitude
    val url =
        "http://maps.google.com/maps?saddr=$officeLatitude,$officeLongitude&daddr=$latitude,$longitude"
    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
    intent.setPackage("com.google.android.apps.maps")

    startActivity(intent)
}

@Preview
@Composable
fun FullVeganPreview() {
    PreviewZeniLunchTheme {
        Restaurant(
            RestaurantUIModel(
                "Chez Audrey",
                "Dev Android",
                "€€€",
                vegetarian = true,
                vegan = true,
                latitude = 1.0,
                longitude = 2.0
            ),
            Modifier
        )
    }
}

@Preview
@Composable
fun FullMeatPreview() {
    PreviewZeniLunchTheme {
        Restaurant(
            RestaurantUIModel(
                "Chez Antho",
                "Couteau Suisse",
                "€€€€€",
                vegetarian = false,
                vegan = false,
                latitude = 1.0,
                longitude = 2.0
            ),
            Modifier
        )
    }
}