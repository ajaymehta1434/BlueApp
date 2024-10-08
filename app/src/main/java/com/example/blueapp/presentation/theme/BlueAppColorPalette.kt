package com.example.blueapp.presentation.theme

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

data class BlueAppColorPalette(
    val tertiaryColor: Color = Color.Unspecified,
    val transparent: Color = Color.Unspecified,
    val darkGrey: Color = Color.Unspecified,
    val accentColor: Color = Color.Unspecified,
    val screenBackground: Color = Color.Unspecified
)

val LocalCustomColorPalette = staticCompositionLocalOf { BlueAppColorPalette() }

val LightCustomColorsPalette = BlueAppColorPalette(
    tertiaryColor = colorTertiary,
    transparent = transparent,
    darkGrey = darkGrey,
    accentColor = colorAccent,
    screenBackground = windowBackground

)
val DarkCustomColorsPalette = BlueAppColorPalette(
    tertiaryColor = colorTertiary,
    transparent = transparent,
    darkGrey = darkGrey,
    accentColor = colorAccent,
    screenBackground = windowBackground
)