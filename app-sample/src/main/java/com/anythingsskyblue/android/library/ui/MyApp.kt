package com.anythingsskyblue.android.library.ui

import android.app.Application
import com.anythingsskyblue.android.library.ui.admob.AdManager

class MyApp: Application() {
    override fun onCreate() {
        super.onCreate()
        AdManager.initialize(this)
    }
}