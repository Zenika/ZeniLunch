package com.zenika.zenilunch.ui.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties

/**
 * A dialog that let the content resize itself.
 */
@Composable
fun AutoResizeDialog(
    properties: DialogProperties = DialogProperties(),
    onDismissRequest: () -> Unit,
    onContentClick: (() -> Unit)? = null,
    content: @Composable BoxScope.() -> Unit
) {
    val dialogInteractionSource = remember { MutableInteractionSource() }
    val contentInteractionSource = remember { MutableInteractionSource() }

    Dialog(
        onDismissRequest = onDismissRequest,
        properties = properties,
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .clickable(
                    interactionSource = dialogInteractionSource,
                    indication = null,
                    onClick = { if (properties.dismissOnClickOutside) onDismissRequest() }
                ),
            contentAlignment = Alignment.Center
        ) {
            Box(
                modifier = Modifier
                    .clickable(
                        indication = null,
                        interactionSource = contentInteractionSource,
                        onClick = { onContentClick?.invoke() }
                    ),
                content = content
            )
        }
    }
}
