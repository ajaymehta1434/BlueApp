package com.example.blueapp.presentation.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.example.blueapp.presentation.theme.LocalCustomColorPalette


@Composable
fun FloatingButton(
    onTap: () -> Unit
) {
    FloatingActionButton(
        onClick = {
            onTap()
        },
        containerColor = LocalCustomColorPalette.current.accentColor
    ) {
        Icon(
            imageVector = Icons.Outlined.Info,
            contentDescription = "Floating Button",
            tint = Color.White
        )
    }
}

@Preview
@Composable
fun PreviewFloatingButton() {
    FloatingButton(
        onTap = { }
    )
}