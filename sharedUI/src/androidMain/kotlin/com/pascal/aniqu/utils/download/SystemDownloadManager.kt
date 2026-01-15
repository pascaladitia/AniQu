package com.pascal.aniqu.utils.download

import android.annotation.SuppressLint
import android.app.DownloadManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.database.Cursor
import android.net.Uri
import android.os.Build
import android.os.Environment
import android.os.Handler
import android.os.Looper
import com.pascal.aniqu.ContextUtils
import kotlin.math.max

class SystemDownloadManager {

    private val context: Context = ContextUtils.context
    private val dm = context.getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager
    private val notif = NotificationHelper(context)
    private val handler = Handler(Looper.getMainLooper())

    @SuppressLint("NewApi")
    fun download(url: String) {
        val fileName = url.substringAfterLast("/").ifBlank { "file.bin" }

        val request = DownloadManager.Request(Uri.parse(url))
            .setTitle(fileName)
            .setAllowedOverMetered(true)
            .setAllowedOverRoaming(true)
            .setNotificationVisibility(DownloadManager.Request.VISIBILITY_HIDDEN)
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

        startProgressPolling(downloadId)
    }

    private fun startProgressPolling(downloadId: Long) {
        val startTime = System.currentTimeMillis()

        handler.post(object : Runnable {
            override fun run() {
                val query = DownloadManager.Query().setFilterById(downloadId)
                val cursor: Cursor = dm.query(query)

                if (cursor.moveToFirst()) {
                    val downloaded =
                        cursor.getLong(cursor.getColumnIndexOrThrow(DownloadManager.COLUMN_BYTES_DOWNLOADED_SO_FAR))
                    val total =
                        cursor.getLong(cursor.getColumnIndexOrThrow(DownloadManager.COLUMN_TOTAL_SIZE_BYTES))
                    val status =
                        cursor.getInt(cursor.getColumnIndexOrThrow(DownloadManager.COLUMN_STATUS))

                    val elapsed = max(1L, System.currentTimeMillis() - startTime)
                    val speed = downloaded * 1000 / elapsed
                    val eta =
                        if (total > 0) (total - downloaded) / max(speed, 1)
                        else 0L

                    val percent =
                        if (total > 0)
                            ((downloaded * 100) / total).toInt().coerceIn(0, 100)
                        else -1

                    when (status) {
                        DownloadManager.STATUS_RUNNING -> {
                            notif.showProgress(percent, downloaded, total, eta)
                            handler.postDelayed(this, 500)
                        }

                        DownloadManager.STATUS_SUCCESSFUL -> {
                            val uriString =
                                cursor.getString(cursor.getColumnIndexOrThrow(DownloadManager.COLUMN_LOCAL_URI))
                            if (uriString != null) {
                                notif.showCompleted(Uri.parse(uriString))
                            } else {
                                notif.showError()
                            }
                        }

                        DownloadManager.STATUS_FAILED -> {
                            notif.showError()
                        }
                    }
                }

                cursor.close()
            }
        })
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
