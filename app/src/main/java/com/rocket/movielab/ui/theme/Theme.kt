package com.rocket.movielab.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext

private val DarkColorScheme = darkColorScheme(
    primary = BrandBase,
    secondary = BrandLight,

    background = Gray100,
    onBackground = Color.White,
    outline = Gray200,
    surface = Gray200,
    onSurface = Gray500,
    onSurfaceVariant = Gray400,
    inverseOnSurface = Gray600,
    surfaceTint = Gray300,
    primaryContainer = Gray300,
    onPrimary = Gray700,
    surfaceContainerLow = Gray700,
    scrim = Shadow,
    error = StatusError
)

private val LightColorScheme = lightColorScheme(
    primary = BrandBase,
    secondary = BrandLight,

    background = Gray100,
    onBackground = Color.White,
    outline = Gray200,
    surface = Gray200,
    onSurface = Gray500,
    onSurfaceVariant = Gray400,
    inverseOnSurface = Gray600,
    surfaceTint = Gray300,
    primaryContainer = Gray300,
    onPrimary = Gray700,
    surfaceContainerLow = Gray700,
    scrim = Shadow,
    error = StatusError
)

@Composable
fun MovieLabTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}