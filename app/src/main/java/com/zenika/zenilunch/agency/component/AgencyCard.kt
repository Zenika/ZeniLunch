package com.zenika.zenilunch.agency.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
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
    side: AgencyImageSide,
    modifier: Modifier = Modifier
) {
    Row(
        modifier
            .semantics(mergeDescendants = true) {}
            .padding(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        when (side) {
            AgencyImageSide.LEFT -> {
                AgencyImage(agency.logoUrl)
                AgencyTitle(agency.name, TextAlign.Start)
            }
            AgencyImageSide.RIGHT -> {
                AgencyTitle(agency.name, TextAlign.End)
                AgencyImage(agency.logoUrl)
            }
        }
    }

}

@Composable
private fun RowScope.AgencyTitle(
    name: String,
    textAlign: TextAlign
) {
    Text(
        text = name,
        textAlign = textAlign,
        style = MaterialTheme.typography.titleLarge,
        modifier = Modifier.Companion.weight(1f)
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

enum class AgencyImageSide {
    LEFT,
    RIGHT
}

@Preview
@Composable
private fun AgencyCardPreview() {
    PreviewZeniLunchTheme {
        Column(Modifier.fillMaxWidth()) {
            AgencyCard(
                agency = Agency(
                    "lyon",
                    "Lyon",
                    "URL",
                    "main/restaurants.json"
                ),
                side = AgencyImageSide.LEFT
            )
            AgencyCard(
                agency =
                Agency(
                    "clermont-ferrand",
                    "Clermont-Ferrand",
                    "URL",
                    "main/restaurants.json"
                ),
                side = AgencyImageSide.RIGHT
            )
        }
    }
}
