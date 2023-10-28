package com.anythingsskyblue.android.library.ui.common

import android.app.Activity
import android.content.ActivityNotFoundException
import android.content.Context
import android.content.ContextWrapper
import android.content.Intent
import android.net.Uri
import androidx.activity.ComponentActivity
import java.util.Locale

object ContextUtil {
    fun findActivity(context: Context): Activity? {
        var result = context
        while (result is ContextWrapper) {
            if (result is Activity) return result
            result = result.baseContext
        }

        return null
    }

    fun findComponentActivity(context: Context): ComponentActivity {
        var result = context
        while (result is ContextWrapper) {
            if (result is ComponentActivity) return result
            result = result.baseContext
        }

        throw IllegalStateException("component activity not found")
    }

    fun openUri(
        context: Context,
        uri: Uri,
        forceBrowser: Boolean = false,
    ){
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
        context.startActivity(intent)
    }

    fun openMarket(
        context: Context,
        applicationId: String,
    ){
        try {
            val uri = Uri.parse(applicationIdToStoreUri(applicationId))
            openUri(context, uri)
        } catch (e: ActivityNotFoundException) {
            val uri = Uri.parse(applicationIdToDownloadUrl(applicationId))
            openUri(context, uri)
        }
    }

    fun applicationIdToDownloadUrl(
        applicationId: String,
    ) = "https://play.google.com/store/apps/details?id=$applicationId&hl=${Locale.getDefault().language}"

    fun applicationIdToStoreUri(
        applicationId: String,
    ) = "market://details?id=$applicationId"

    fun shareText(context: Context, text: String){
        val sharingIntent = Intent(Intent.ACTION_SEND)
        sharingIntent.type = "text/plain"
        sharingIntent.putExtra(Intent.EXTRA_TEXT, text)

        context.startActivity(Intent.createChooser(sharingIntent, "URL을 공유하기"))
    }
}