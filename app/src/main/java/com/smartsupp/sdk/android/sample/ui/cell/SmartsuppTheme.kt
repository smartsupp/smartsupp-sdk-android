package com.smartsupp.sdk.android.sample.ui.cell

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.graphics.Color

@Composable
fun SmartsuppTheme(
    content: @Composable () -> Unit,
) {
    CompositionLocalProvider {
        MaterialTheme(
            colorScheme = lightColorScheme(
                surfaceVariant = Color.White,
                surface = Color(0xFFF3F1F8),
                background = Color(0xFFF3F1F8),
            ),
            content = content,
        )
    }
}
