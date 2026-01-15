package com.pascal.aniqu.utils.download

import android.annotation.SuppressLint
import android.app.DownloadManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Build
import android.os.Environment
import androidx.core.net.toUri
import com.pascal.aniqu.ContextUtils

class SystemDownloadManager {

    private val context: Context = ContextUtils.context
    private val dm = context.getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager

    @SuppressLint("NewApi")
    fun download(url: String) {
        val fileName = url.substringAfterLast("/").ifBlank { "file.bin" }

        val request = DownloadManager.Request(url.toUri())
            .setTitle(fileName)
            .setAllowedOverMetered(true)
            .setAllowedOverRoaming(true)
            .setNotificationVisibility(
                DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED
            )
            .setDestinationInExternalPublicDir(
                Environment.DIRECTORY_DOWNLOADS,
                fileName
            )

        val downloadId = dm.enqueue(request)

        context.registerReceiver(
            DownloadCompleteReceiver(downloadId),
            IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE),
            if (Build.VERSION.SDK_INT >= 33)
                Context.RECEIVER_NOT_EXPORTED else 0
        )
    }

    private inner class DownloadCompleteReceiver(
        private val downloadId: Long
    ) : BroadcastReceiver() {

        override fun onReceive(context: Context, intent: Intent) {
            val id = intent.getLongExtra(DownloadManager.EXTRA_DOWNLOAD_ID, -1)
            if (id != downloadId) return
            context.unregisterReceiver(this)
        }
    }
}
