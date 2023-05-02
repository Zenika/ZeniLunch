package com.zenika.zenilunch.list

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
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment.Companion.BottomCenter
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.zenika.zenilunch.R
import com.zenika.zenilunch.RestaurantUIModel
import com.zenika.zenilunch.ageny.model.Agency
import com.zenika.zenilunch.ui.theme.screenPadding
import kotlinx.collections.immutable.ImmutableList

@Composable
fun ListScreen(
    goToDetailScreen: (restaurant: RestaurantUIModel) -> Unit,
    openSuggestionDialog: (restaurant: RestaurantUIModel) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: ListViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.init()
    }

    when (val theState = state) {
        ListUiState.Loading -> Text(
            text = "Loading",
            modifier = Modifier.fillMaxWidth()
        )

        is ListUiState.Loaded -> ListContent(
            theState.agency,
            theState.restaurants,
            goToDetailScreen,
            openSuggestionDialog
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListContent(
    agency: Agency,
    restaurants: ImmutableList<RestaurantUIModel>,
    goToDetailScreen: (restaurant: RestaurantUIModel) -> Unit,
    openSuggestionDialog: (restaurant: RestaurantUIModel) -> Unit,
) {
    Scaffold(
        modifier = modifier,
        topBar = {
            TopAppBar(title = { Text(text = stringResource(R.string.list_title, agency.name)) })
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
                    style = MaterialTheme.typography.titleLarge
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
                    state = rememberLazyListState()
                ) {
                    items(restaurants.size) { index ->
                        val restaurant = restaurants[index]
                        RestaurantCard(goToDetailScreen, restaurant)
                    }
                }
            }
        },
        bottomBar = {
            SuggestionButton(restaurants, openSuggestionDialog)
        }
    )
}

@Composable
private fun SuggestionButton(
    restaurants: ImmutableList<RestaurantUIModel>,
    openSuggestionDialog: (restaurant: RestaurantUIModel) -> Unit
) {
    Box(Modifier.fillMaxWidth()) {
        Button(
            modifier = Modifier
                .align(BottomCenter)
                .padding(screenPadding),
            onClick = {
                val restaurant = restaurants.random()
                openSuggestionDialog(restaurant)
            }) {
            Text(
                text = stringResource(R.string.suggestion),
                style = MaterialTheme.typography.headlineSmall
            )
        }
    }
}
