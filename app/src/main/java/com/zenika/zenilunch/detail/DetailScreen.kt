package com.zenika.zenilunch.detail

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.zenika.zenilunch.R
import com.zenika.zenilunch.RestaurantUIModel
import com.zenika.zenilunch.ui.theme.PreviewZeniLunchTheme
import com.zenika.zenilunch.ui.theme.screenPadding

const val OFFICE_LATITUDE = 45.766752337134754
const val OFFICE_LONGITUDE = 4.858952442403011

@Composable
fun DetailScreen(
    popBack: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: DetailViewModel = hiltViewModel()
) {
    val restaurant by viewModel.restaurant.collectAsState()
    Restaurant(
        restaurant,
        popBack,
        viewModel::hideRestaurant,
        modifier
            .fillMaxSize()
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun Restaurant(
    restaurant: RestaurantUIModel,
    popBack: () -> Unit,
    hideRestaurant: () -> Unit,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    Scaffold(
        topBar = {
            Text(
                text = restaurant.name,
                modifier = Modifier
                    .fillMaxWidth()
                    .background(color = MaterialTheme.colorScheme.primary)
                    .padding(16.dp),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.headlineSmall,
                color = MaterialTheme.colorScheme.onPrimary
            )
        }
    ) { innerPadding ->
        Column(
            modifier
                .padding(
                    top = innerPadding.calculateTopPadding(),
                    start = innerPadding.calculateStartPadding(LocalLayoutDirection.current),
                    end = innerPadding.calculateEndPadding(LocalLayoutDirection.current)
                )
                .padding(screenPadding),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text(
                text = restaurant.type,
                style = MaterialTheme.typography.bodyLarge
            )
            Text(
                text = restaurant.price,
                style = MaterialTheme.typography.bodyLarge
            )
            Text(
                text = extractOptions(restaurant),
                style = MaterialTheme.typography.bodyLarge
            )
            OpenGoogleMapsButton(onClick = { context.openGoogleMaps(restaurant) })
            HideRestaurantButton(
                onClick = {
                    hideRestaurant()
                    popBack()
                }
            )
        }
    }
}

@Composable
private fun OpenGoogleMapsButton(
    onClick: () -> Unit
) {
    Button(onClick = onClick) {
        Text(
            text = stringResource(R.string.map),
            style = MaterialTheme.typography.bodyLarge
        )
    }
}

@Composable
private fun HideRestaurantButton(
    onClick: () -> Unit
) {
    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.error
        )
    ) {
        Text(
            text = stringResource(R.string.hide),
            style = MaterialTheme.typography.bodyMedium
        )
    }
}

@Composable
private fun extractOptions(restaurant: RestaurantUIModel): String {
    val option = when {
        restaurant.vegan -> stringResource(R.string.option, stringResource(R.string.vegan))
        restaurant.vegetarian -> stringResource(
            R.string.option,
            stringResource(R.string.vegetarian)
        )

        else -> stringResource(R.string.noOption)
    }
    return option
}

fun Context.openGoogleMaps(restaurant: RestaurantUIModel) {
    val latitude = restaurant.latitude
    val longitude = restaurant.longitude
    val url =
        "http://maps.google.com/maps?saddr=$OFFICE_LATITUDE,$OFFICE_LONGITUDE&daddr=$latitude,$longitude"
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
            popBack = { Log.d("preview", "Restaurant caché") },
            hideRestaurant = {},
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
            popBack = { Log.d("preview", "Restaurant caché") },
            hideRestaurant = {},
            Modifier
        )
    }
}
