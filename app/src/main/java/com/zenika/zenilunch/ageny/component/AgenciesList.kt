package com.zenika.zenilunch.ageny.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.zenika.zenilunch.R
import com.zenika.zenilunch.ageny.model.Agency
import com.zenika.zenilunch.ui.theme.PreviewZeniLunchTheme
import com.zenika.zenilunch.ui.theme.screenPadding
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf

@Composable
fun AgenciesList(
    agencies: ImmutableList<Agency>,
    onAgencyClick: (Agency) -> Unit,
    modifier: Modifier
) {
    LazyColumn(
        modifier,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        item {
            Text(
                text = stringResource(R.string.agencySelection_title),
                style = MaterialTheme.typography.headlineMedium,
                modifier = Modifier.padding(screenPadding)
            )
        }
        itemsIndexed(agencies) { index, agency ->
            AgencyCard(
                agency = agency,
                side = if (index % 2 == 0) AgencyImageSide.LEFT else AgencyImageSide.RIGHT,
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { onAgencyClick(agency) }
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
