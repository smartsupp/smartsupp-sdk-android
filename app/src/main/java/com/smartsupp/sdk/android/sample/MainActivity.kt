package com.smartsupp.sdk.android.sample

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.smartsupp.mobile.sdk.android.Smartsupp
import com.smartsupp.mobile.sdk.android.data.AccountStatus
import com.smartsupp.mobile.sdk.android.ui.app.SmartsuppRootActivity

class MainActivity: ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            Scaffold {
                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(it),
                ) {
                    val unreadMessageCount = Smartsupp.instance().unreadMessageCount().collectAsState().value
                    val accountStatus = Smartsupp.instance().accountStatus().collectAsState().value

                    DataField(
                        text = "Unread Message Count:",
                        value = unreadMessageCount.toString(),
                        backgroundColor = Color.Blue,
                    )
                    DataField(
                        text = "Account status:",
                        value = accountStatus.key,
                        backgroundColor = if (accountStatus == AccountStatus.OFFLINE) Color.Red else Color.Green,
                    )

                    Button(
                        modifier = Modifier.padding(top = 8.dp),
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
                        content = {
                            Text(text = "Open Chat box")
                        },
                    )
                }
            }
        }
    }

    @Composable
    private fun DataField(text: String, value: String, backgroundColor: Color) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(top = 4.dp),
        ) {
            Text(
                text = text,
                style = MaterialTheme.typography.titleMedium,
            )

            Text(
                text = value,
                style = MaterialTheme.typography.titleMedium,
                color = Color.White,
                modifier = Modifier
                    .padding(start = 4.dp)
                    .background(color = backgroundColor, shape = CircleShape)
                    .padding(horizontal = 8.dp, vertical = 4.dp),
            )
        }
    }
}