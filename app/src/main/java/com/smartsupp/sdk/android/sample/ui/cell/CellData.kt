package com.smartsupp.sdk.android.sample.ui.cell

import androidx.compose.ui.graphics.Color

sealed class CellData(val text: String) {

    class Value(
        text: String,
        val value: String,
        val valueColor: Color,
    ) : CellData(text = text)

    class Action(
        text: String,
        val onClick: () -> Unit,
    ) : CellData(text = text)

    class Button(
        text: String,
        val onClick: () -> Unit,
    ) : CellData(text = text)
}
