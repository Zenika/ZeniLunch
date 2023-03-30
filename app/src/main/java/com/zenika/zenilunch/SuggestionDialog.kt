package com.zenika.zenilunch

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun SuggestionDialog(
    viewModel: RestaurantViewModel = hiltViewModel()
) {
    val openDialog = remember { mutableStateOf(true) }
    val restaurant by viewModel.restaurant.collectAsState()
    if (openDialog.value) {
        Dialog(
            onDismissRequest = { openDialog.value = false },
            properties = DialogProperties(dismissOnBackPress = true, dismissOnClickOutside = true)
        ) {
            Column(
                Modifier
                    .fillMaxWidth()
                    .background(color = MaterialTheme.colorScheme.primary, RoundedCornerShape(24.dp)),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = restaurant.name,
                    Modifier
                        .padding(12.dp),
                    fontSize = 24.sp,
                    color = MaterialTheme.colorScheme.onPrimary
                )
                Text(
                    text = restaurant.type,
                    Modifier
                        .padding(12.dp),
                    fontSize = 20.sp,
                    color = MaterialTheme.colorScheme.onPrimary
                )
            }
        }
    }
}
