package com.pascal.aniqu.utils

import android.content.Intent
import android.os.Build
import android.os.Handler
import android.os.Looper
import android.widget.Toast
import com.pascal.aniqu.ContextUtils

@Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")
actual object AppInfo {
    actual val versionName: String
        get() = ContextUtils.context.packageManager
            .getPackageInfo(ContextUtils.context.packageName, 0)
            .versionName ?: ""

    actual val versionCode: String
        get() = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            ContextUtils.context.packageManager
                .getPackageInfo(ContextUtils.context.packageName, 0)
                .longVersionCode.toString()
        } else {
            ContextUtils.context.packageManager
                .getPackageInfo(ContextUtils.context.packageName, 0)
                .versionCode.toString()
        }

    actual val appId: String
        get() = ContextUtils.context.packageName
}

actual fun showToast(msg: String) {
    Toast.makeText(ContextUtils.context, msg, Toast.LENGTH_SHORT).show()
}

actual fun actionShareUrl(url: String?) {
    if (url.isNullOrEmpty()) return

    val context = ContextUtils.context

    Handler(Looper.getMainLooper()).post {
        try {
            val shareIntent = Intent(Intent.ACTION_SEND).apply {
                type = "text/plain"
                putExtra(Intent.EXTRA_TEXT, url)
            }

            val chooser = Intent.createChooser(shareIntent, "Bagikan link ke...")
            chooser.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            context.startActivity(chooser)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}

actual fun downloadDirectory(): String =
    android.os.Environment
        .getExternalStoragePublicDirectory(
            android.os.Environment.DIRECTORY_DOWNLOADS
        ).absolutePath
