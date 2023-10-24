package com.anythingsskyblue.android.library.ui.util

import android.app.Activity
import android.content.ActivityNotFoundException
import android.content.Context
import android.content.ContextWrapper
import android.content.Intent
import android.net.Uri
import androidx.activity.ComponentActivity

fun Context.findActivity(): Activity? {
    var context = this
    while (context is ContextWrapper) {
        if (context is Activity) return context
        context = context.baseContext
    }

    return null
}

fun Context.findComponentActivity(): ComponentActivity {
    var context = this
    while (context is ContextWrapper) {
        if (context is ComponentActivity) return context
        context = context.baseContext
    }

    throw IllegalStateException("component activity not found")
}

fun Context.openUri(uri: Uri, forceBrowser: Boolean = false){
    val intent = if (forceBrowser){
        Intent(Intent.ACTION_VIEW).apply {
            data = uri
            flags = Intent.FLAG_ACTIVITY_NEW_TASK
        }
    }else{
        Intent(Intent.ACTION_VIEW, uri).apply {
            addCategory(Intent.CATEGORY_BROWSABLE)
        }
    }
    startActivity(intent)
}

fun Context.openMarket(applicationId: String){
    try {
        val uri = Uri.parse("market://details?id=$applicationId")
        openUri(uri)
    } catch (e: ActivityNotFoundException) {
        val uri = Uri.parse("https://play.google.com/store/apps/details?id=$applicationId")
        openUri(uri)
    }
}