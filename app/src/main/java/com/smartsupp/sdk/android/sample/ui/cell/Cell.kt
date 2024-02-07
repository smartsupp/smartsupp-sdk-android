package com.smartsupp.sdk.android.sample.ui.cell

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.capitalize
import androidx.compose.ui.text.intl.Locale
import androidx.compose.ui.unit.dp

@Composable
fun SectionCell(
    title: String,
    vararg data: CellData,
) {
    Column {
        Text(
            text = title,
            color = Color.DarkGray,
            modifier = Modifier.padding(start = 26.dp, top = 16.dp, bottom = 4.dp),
        )

        Column(
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .background(
                    color = Color.White,
                    shape = RoundedCornerShape(8.dp),
                ),
        ) {
            data.forEachIndexed { index, cellData ->
                val isSingle = data.size == 1
                val isFirst = index == 0
                val isLast = index == data.size - 1

                Cell(
                    shape = when {
                        isSingle -> RoundedCornerShape(8.dp)
                        isFirst -> RoundedCornerShape(topStart = 8.dp, topEnd = 8.dp)
                        isLast -> RoundedCornerShape(bottomStart = 8.dp, bottomEnd = 8.dp)
                        else -> RectangleShape
                    },
                    showDivider = !isSingle && !isLast,
                    content = {
                        when (cellData) {
                            is CellData.Action -> {
                                ActionCell(cellData)
                            }

                            is CellData.Button -> {
                                ButtonCell(cellData)
                            }

                            is CellData.Value -> {
                                ValueCell(cellData)
                            }
                        }
                    },
                )
            }
        }
    }
}

@Composable
fun ValueCell(cellData: CellData.Value) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.padding(16.dp),
    ) {
        Text(
            text = cellData.text,
            style = MaterialTheme.typography.titleMedium,
        )

        Spacer(modifier = Modifier.weight(1f))

        Text(
            text = cellData.value.capitalize(Locale.current),
            style = MaterialTheme.typography.titleMedium,
            color = cellData.valueColor,
            modifier = Modifier,
        )
    }
}

@Composable
fun ButtonCell(
    cellData: CellData.Button,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                cellData.onClick()
            }
            .padding(16.dp),
    ) {
        Text(
            text = cellData.text,
            style = MaterialTheme.typography.titleMedium,
            color = Color.Blue,
        )
    }
}

@Composable
fun ActionCell(
    cellData: CellData.Action,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                cellData.onClick()
            }
            .padding(16.dp),
    ) {
        Text(
            text = cellData.text,
            style = MaterialTheme.typography.titleMedium,
        )

        Spacer(modifier = Modifier.weight(1f))

        Icon(
            imageVector = Icons.Filled.KeyboardArrowRight,
            contentDescription = null,
            tint = Color.Gray,
        )
    }
}

@Composable
fun Cell(
    shape: Shape,
    showDivider: Boolean,
    content: @Composable () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(
                color = Color.White,
                shape = shape,
            ),
    ) {
        content()

        if (showDivider) {
            Divider(
                modifier = Modifier
                    .fillMaxWidth()
                    .alpha(0.3f)
                    .padding(start = 8.dp),
            )
        }
    }
}
