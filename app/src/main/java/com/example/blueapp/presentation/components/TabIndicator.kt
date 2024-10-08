package com.example.blueapp.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.blueapp.R
import com.example.blueapp.presentation.theme.LocalCustomColorPalette

@Composable
fun TabIndicator(
    activeTab: Boolean
) {
    val color = if (activeTab) {
        MaterialTheme.colorScheme.primary
    } else {
        LocalCustomColorPalette.current.darkGrey
    }
    Box(
        modifier = Modifier
            .padding(horizontal = dimensionResource(id = R.dimen.dp_4))
            .size(dimensionResource(id = R.dimen.dp_10))
            .background(color = color, shape = CircleShape)
    ) {

    }
}

@Preview
@Composable
fun PreviewTabIndiCator() {
    TabIndicator(activeTab = true)
}