package com.smartsupp.sdk.android.sample

import android.app.Application
import com.smartsupp.mobile.sdk.android.Smartsupp

class MainApplication: Application() {

    override fun onCreate() {
        super.onCreate()

        Smartsupp.setup(
            application = this,
            smartsuppKey = TODO("Add Smartsupp Key"),
            apiKey = TODO("Add Api Key")
        )
    }
}