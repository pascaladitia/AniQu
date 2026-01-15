package com.pascal.aniqu.utils.download

import android.annotation.SuppressLint
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.provider.DocumentsContract
import android.provider.MediaStore
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import java.util.concurrent.TimeUnit

class NotificationHelper(private val context: Context) {

    private val channelId = "download_channel"
    private val notifId = 1001
    private val manager = NotificationManagerCompat.from(context)

    init {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = android.app.NotificationChannel(
                channelId,
                "Download",
                android.app.NotificationManager.IMPORTANCE_LOW
            )
            manager.createNotificationChannel(channel)
        }
    }

    @SuppressLint("MissingPermission")
    fun showProgress(percent: Int, downloadedBytes: Long, totalBytes: Long, etaSeconds: Long) {
        val downloadedMB = downloadedBytes / 1024 / 1024
        val totalMB = if (totalBytes > 0) totalBytes / 1024 / 1024 else -1
        val etaText = if (etaSeconds > 0) formatTime(etaSeconds) else "--:--"

        val contentText = if (totalMB > 0) {
            "$downloadedMB MB / $totalMB MB, ETA $etaText"
        } else {
            "$downloadedMB MB downloaded, ETA $etaText"
        }

        val builder = NotificationCompat.Builder(context, channelId)
            .setSmallIcon(android.R.drawable.stat_sys_download)
            .setContentTitle(if (percent >= 0) "Downloading... $percent%" else "Downloading...")
            .setContentText(contentText)
            .setOngoing(true)
            .setOnlyAlertOnce(true)

        if (percent >= 0) {
            builder.setProgress(100, percent, false)
        } else {
            builder.setProgress(0, 0, true)
        }

        manager.notify(notifId, builder.build())
    }

    @SuppressLint("NewApi", "MissingPermission")
    fun showCompleted(uri: Uri) {
        val intent = Intent(Intent.ACTION_VIEW).apply {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                setDataAndType(
                    MediaStore.Downloads.EXTERNAL_CONTENT_URI,
                    DocumentsContract.Document.MIME_TYPE_DIR
                )
            } else {
                flags = Intent.FLAG_ACTIVITY_NEW_TASK
            }
        }

        val pending = PendingIntent.getActivity(
            context,
            0,
            intent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )

        val notif = NotificationCompat.Builder(context, channelId)
            .setSmallIcon(android.R.drawable.stat_sys_download_done)
            .setContentTitle("Download Complete")
            .setContentText(uri.lastPathSegment ?: "File downloaded")
            .setContentIntent(pending)
            .setAutoCancel(true)
            .build()

        manager.notify(notifId, notif)
    }

    @SuppressLint("MissingPermission")
    fun showError() {
        val notif = NotificationCompat.Builder(context, channelId)
            .setSmallIcon(android.R.drawable.stat_notify_error)
            .setContentTitle("Download Failed")
            .setAutoCancel(true)
            .build()

        manager.notify(notifId, notif)
    }

    private fun formatTime(seconds: Long): String {
        val m = TimeUnit.SECONDS.toMinutes(seconds)
        val s = seconds - TimeUnit.MINUTES.toSeconds(m)
        return "%02d:%02d".format(m, s)
    }
}
