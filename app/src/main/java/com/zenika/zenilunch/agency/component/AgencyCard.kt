package com.zenika.zenilunch.agency.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.ColorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.zenika.zenilunch.agency.model.Agency
import com.zenika.zenilunch.ui.theme.PreviewZeniLunchTheme

@Composable
fun AgencyCard(
    agency: Agency,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Card(modifier) {
        Column(
            Modifier
                .fillMaxWidth()
                .clickable(onClick = onClick)
                .semantics(mergeDescendants = true) {}
                .padding(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            AgencyImage(agency.logoUrl)
            AgencyTitle(agency.name, TextAlign.Start)
        }
    }
}

@Composable
private fun AgencyTitle(
    name: String,
    textAlign: TextAlign
) {
    Text(
        text = name,
        textAlign = textAlign,
        style = MaterialTheme.typography.titleMedium
    )
}

@Composable
private fun AgencyImage(
    logoUrl: String,
    modifier: Modifier = Modifier
) {
    if (LocalInspectionMode.current) {
        Box(
            modifier
                .size(64.dp)
                .clip(CircleShape)
                .background(Color.LightGray)
        )
    } else {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(logoUrl)
                .crossfade(true)
                .build(),
            placeholder = ColorPainter(Color.LightGray),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = modifier
                .size(64.dp)
                .clip(CircleShape)
        )
    }
}

@Preview
@Composable
private fun AgencyCardPreview() {
    PreviewZeniLunchTheme {
        Column(
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            AgencyCard(
                agency =
                Agency(
                    "clermont-ferrand",
                    "Clermont-Ferrand",
                    "URL",
                    "main/restaurants.json"
                ),
                onClick = {}
            )
            AgencyCard(
                agency = Agency(
                    "lyon",
                    "Lyon",
                    "URL",
                    "main/restaurants.json"
                ),
                onClick = {}
            )
        }
    }
}
