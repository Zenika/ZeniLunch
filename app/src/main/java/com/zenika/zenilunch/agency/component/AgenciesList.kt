package com.zenika.zenilunch.agency.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.zenika.zenilunch.R
import com.zenika.zenilunch.agency.model.Agency
import com.zenika.zenilunch.ui.theme.PreviewZeniLunchTheme
import com.zenika.zenilunch.ui.theme.screenPadding
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf

@Composable
fun AgenciesList(
    agencies: ImmutableList<Agency>,
    modifier: Modifier = Modifier,
    onAgencyClick: (Agency) -> Unit
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = modifier
            .padding(horizontal = screenPadding),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        item(span = { GridItemSpan(2) }) {
            Text(
                text = stringResource(R.string.agencySelection_title),
                style = MaterialTheme.typography.headlineMedium,
                modifier = Modifier.padding(screenPadding)
            )
        }
        items(agencies) { agency ->
            AgencyCard(
                agency = agency,
                modifier = Modifier
                    .fillMaxWidth(),
                onClick = { onAgencyClick(agency) }
            )
        }
    }
}

@Preview
@Composable
private fun AgenciesListPreview() {
    PreviewZeniLunchTheme {
        AgenciesList(
            agencies = persistentListOf(
                Agency(
                    "lyon",
                    "Lyon",
                    "URL",
                    "main/restaurants.json"
                ),
                Agency(
                    "clermont-ferrand",
                    "Clermont-Ferrand",
                    "URL",
                    "main/restaurants.json"
                )
            ),
            onAgencyClick = {},
            modifier = Modifier.fillMaxSize()
        )
    }
}
