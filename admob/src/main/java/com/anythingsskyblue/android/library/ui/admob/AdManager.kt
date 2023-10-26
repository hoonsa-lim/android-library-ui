package com.anythingsskyblue.android.library.ui.admob

import android.content.Context
import com.google.android.gms.ads.MobileAds

object AdManager {
    fun initialize(context: Context){
        MobileAds.initialize(context) {}
    }
}