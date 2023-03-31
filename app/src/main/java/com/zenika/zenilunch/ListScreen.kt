package com.zenika.zenilunch

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.BottomCenter
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.zenika.zenilunch.ui.theme.screenPadding
import kotlin.random.Random

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListScreen(
    onRestaurantClick: (restaurant: RestaurantUIModel) -> Unit,
    onSuggestionClick: (restaurant: RestaurantUIModel) -> Unit
) {
    val restaurants = createRestaurants()
    val state = rememberLazyListState()

    Scaffold(
        topBar = {
            TopAppBar(title = { Text(text = "ZeniLunch") })
        },
        content = { innerPadding ->
            Column(
                Modifier
                    .padding(
                        top = innerPadding.calculateTopPadding(),
                        start = innerPadding.calculateStartPadding(LocalLayoutDirection.current),
                        end = innerPadding.calculateEndPadding(LocalLayoutDirection.current)
                    )
            ) {
                Text(
                    text = stringResource(R.string.description),
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.bodyLarge
                )
                LazyColumn(
                    Modifier
                        .fillMaxSize(),
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                    horizontalAlignment = CenterHorizontally,
                    contentPadding = PaddingValues(
                        start = screenPadding,
                        top = screenPadding,
                        end = screenPadding,
                        bottom = screenPadding + innerPadding.calculateBottomPadding(),
                    ),
                    state = state
                ) {
                    items(restaurants.size) { index ->
                        val restaurant = restaurants[index]
                        val restaurantName = restaurant.name
                        Card(
                            Modifier
                                .fillMaxWidth()
                                .clickable {
                                    onRestaurantClick(restaurant)
                                }
                        ) {
                            Text(
                                text = restaurantName, Modifier
                                    .padding(24.dp)
                                    .fillMaxSize(),
                                fontSize = 20.sp,
                                textAlign = TextAlign.Center
                            )
                        }
                    }
                }
            }
        },
        bottomBar = {
            Box(Modifier.fillMaxWidth()) {
                Button(
                    modifier = Modifier.align(BottomCenter).padding(screenPadding),
                    onClick = {
                        val randomIndex = Random.nextInt(restaurants.size)
                        val restaurant = restaurants[randomIndex]
                        onSuggestionClick(restaurant)
                    }) {
                    Text(
                        text = stringResource(R.string.suggestion),
                        fontSize = 20.sp
                    )
                }
            }
        }
    )
}

private fun createRestaurants(): List<RestaurantUIModel> {
    return listOf(
        RestaurantUIModel(
            "Kaffee Berlin",
            "Burger",
            "€€",
            vegetarian = true,
            vegan = true,
            45.767551889608235,
            4.857335592897319
        ),
        RestaurantUIModel(
            "Happy Feel",
            "Végétarien",
            "€€",
            vegetarian = true,
            vegan = true,
            45.76864100678723,
            4.8619654828776016
        ),
        RestaurantUIModel(
            "Chez Jules",
            "Boulangerie",
            "€",
            vegetarian = false,
            vegan = false,
            45.76648502742043,
            4.856709398222179
        ),
        RestaurantUIModel(
            "O Pad Thaï",
            "Thaïlandais",
            "€€",
            vegetarian = false,
            vegan = false,
            45.76399480415859,
            4.856358936709006
        ),
        RestaurantUIModel(
            "Jojo Pizza",
            "Pizzeria",
            "€",
            vegetarian = true,
            vegan = false,
            45.77004280935307,
            4.858317698222286
        ),
        RestaurantUIModel(
            "Ok Sushi",
            "Japonais",
            "€€",
            true,
            false,
            45.76855096798334,
            4.8494913730169795
        ),
        RestaurantUIModel(
            "Chikin Bang",
            "Coréen",
            "€",
            false,
            false,
            45.761726612268745,
            4.856067793275381
        ),
        RestaurantUIModel(
            "Manger Vite & Bien",
            "Bar à salades",
            "€",
            true,
            true,
            45.764033379006165,
            4.857152298222151
        ),
        RestaurantUIModel(
            "Subway",
            "Sandwich",
            "€€",
            true,
            true,
            45.76959922404694,
            4.854517083590193
        ),
        RestaurantUIModel(
            "Pepe Pizza",
            "Pizzeria",
            "€€",
            false,
            false,
            45.76609019074048,
            4.854910608843241
        )
    )
}