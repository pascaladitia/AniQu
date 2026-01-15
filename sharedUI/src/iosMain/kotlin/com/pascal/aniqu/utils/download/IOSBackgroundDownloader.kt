package com.pascal.aniqu.utils.download

import kotlinx.cinterop.ExperimentalForeignApi
import platform.Foundation.*
import platform.darwin.NSObject

object IOSBackgroundDownloader : NSObject(), NSURLSessionDownloadDelegateProtocol {

    private val session: NSURLSession by lazy {
        val config = NSURLSessionConfiguration.backgroundSessionConfigurationWithIdentifier(
            "com.pascal.aniqu.bgdownload"
        )
        config.sessionSendsLaunchEvents = true
        config.discretionary = true
        NSURLSession.sessionWithConfiguration(
            configuration = config,
            delegate = this,
            delegateQueue = NSOperationQueue.mainQueue
        )
    }

    fun download(url: String) {
        val nsUrl = NSURL(string = url) ?: return
        val task = session.downloadTaskWithURL(nsUrl)
        task.resume()
    }

    @OptIn(ExperimentalForeignApi::class)
    override fun URLSession(
        session: NSURLSession,
        downloadTask: NSURLSessionDownloadTask,
        didFinishDownloadingToURL: NSURL
    ) {
        val fileManager = NSFileManager.defaultManager
        val documentsArray = fileManager.URLsForDirectory(NSDocumentDirectory, NSUserDomainMask)
        val documents = documentsArray.get(0) as? NSURL ?: return

        val fileName = downloadTask.originalRequest?.URL?.lastPathComponent ?: "file.dat"
        val dest = documents.URLByAppendingPathComponent(fileName)

        if (dest?.path != null && fileManager.fileExistsAtPath(dest.path!!)) {
            fileManager.removeItemAtURL(dest, null)
        }

        val moved = fileManager.moveItemAtURL(didFinishDownloadingToURL, dest!!, null)

        if (moved) {
            showCompletedNotification(dest?.path ?: "")
        }
    }

    private fun showCompletedNotification(filePath: String) {
//        val center = UNUserNotificationCenter.currentNotificationCenter
//
//        val content = UNMutableNotificationContent().also {
//            it.title = "Download Complete"
//            it.body = "Tap to open file"
//            it.sound = UNNotificationSound.defaultSound
//        }
//
//        val trigger = UNTimeIntervalNotificationTrigger.triggerWithTimeInterval(1.0, repeats = false)
//
//        val request = UNNotificationRequest.requestWithIdentifier(
//            identifier = "download_done",
//            content = content,
//            trigger = trigger
//        )
//
//        center.addNotificationRequest(request) { error ->
//            if (error != null) {
//                println("Notification error: ${error.localizedDescription}")
//            }
//        }
    }
}
