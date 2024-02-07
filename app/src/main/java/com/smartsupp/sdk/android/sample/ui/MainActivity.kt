package com.smartsupp.sdk.android.sample.ui

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.collectAsState
import com.smartsupp.mobile.sdk.android.Smartsupp
import com.smartsupp.mobile.sdk.android.data.AccountStatus
import com.smartsupp.sdk.android.sample.ui.cell.SmartsuppTheme
import kotlinx.coroutines.flow.MutableStateFlow

class MainActivity : ComponentActivity() {

    private val unreadMessageCountStateFlow = MutableStateFlow(0)
    private val accountStatusStateFlow = MutableStateFlow(AccountStatus.OFFLINE)

    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Smartsupp.instance().addAccountStatusListener {
            accountStatusStateFlow.value = it
        }

        Smartsupp.instance().addUnreadMessageCountListener {
            unreadMessageCountStateFlow.value = it
        }

        setContent {
            SmartsuppTheme {
                Scaffold {
                    MainScreen(
                        unreadMessageCount = unreadMessageCountStateFlow.collectAsState().value,
                        accountStatus = accountStatusStateFlow.collectAsState().value
                    )
                }
            }
        }
    }
}