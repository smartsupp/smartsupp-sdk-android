package com.smartsupp.sdk.android.sample.ui

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.smartsupp.mobile.sdk.android.Smartsupp
import com.smartsupp.mobile.sdk.android.data.AccountStatus
import com.smartsupp.sdk.android.sample.ui.cell.CellData
import com.smartsupp.sdk.android.sample.ui.cell.SectionCell

@Composable
fun MainScreen(
    unreadMessageCount: Int,
    accountStatus: AccountStatus
) {

    Column() {
        Text(
            text = "Smartsupp SDK",
            style = MaterialTheme.typography.titleLarge.copy(
                fontWeight = FontWeight.ExtraBold,
                fontSize = 36.sp,
            ),
            textAlign = TextAlign.Start,
            modifier = Modifier.padding(
                16.dp,
            ),
        )

        SectionCell(
            title = "Widget",
            data = arrayOf(
                CellData.Button(
                    text = "Open Chat Box",
                    onClick = {
                        Smartsupp.instance().openChatBox(
                            onFailureCallback = { error ->
                                Log.e("SmartsuppSdk", error.stackTraceToString())
                            },
                            onSuccessCallback = {
                                Log.d("SmartsuppSdk", "Smartsupp opened")
                            },
                        )
                    },
                ),
            ),
        )

        SectionCell(
            title = "Status",
            data = arrayOf(
                CellData.Value(
                    text = "Unread Messages",
                    value = unreadMessageCount.toString(),
                    valueColor = if (unreadMessageCount > 0) Color.Green else Color.Gray,
                ),

                CellData.Value(
                    text = "Account status",
                    value = accountStatus.key,
                    valueColor = if (accountStatus == AccountStatus.OFFLINE) Color.Red else Color.Green,
                ),
            ),
        )
    }
}