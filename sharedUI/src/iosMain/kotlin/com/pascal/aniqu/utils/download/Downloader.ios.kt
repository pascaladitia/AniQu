package com.pascal.aniqu.utils.download

import co.touchlab.kermit.Logger
import kotlinx.cinterop.ExperimentalForeignApi
import platform.Foundation.*
import platform.darwin.NSObject

actual class DownloadManager actual constructor() : Downloader {

    private val logger = Logger.withTag("DownloadManager-iOS")
    private val delegate = DownloadDelegate(logger)

    private val session: NSURLSession by lazy {
        logger.i("Creating native NSURLSession (delegate based)")
        NSURLSession.sessionWithConfiguration(
            configuration = NSURLSessionConfiguration.defaultSessionConfiguration(),
            delegate = delegate,
            delegateQueue = NSOperationQueue.mainQueue()
        )
    }

    @OptIn(ExperimentalForeignApi::class)
    actual override fun download(url: String) {
        logger.i("Download called: $url")

        val nsUrl = NSURL.URLWithString(url)
        if (nsUrl == null) {
            logger.e("Invalid URL")
            return
        }

        val request = NSURLRequest.requestWithURL(nsUrl)
        val task = session.downloadTaskWithRequest(request)

        logger.i("Resuming download task")
        task.resume()
    }
}

private class DownloadDelegate(
    private val logger: Logger
) : NSObject(), NSURLSessionDownloadDelegateProtocol {

    @OptIn(ExperimentalForeignApi::class)
    override fun URLSession(
        session: NSURLSession,
        downloadTask: NSURLSessionDownloadTask,
        didFinishDownloadingToURL: NSURL
    ) {
        logger.i("Download finished (delegate)")

        val fileManager = NSFileManager.defaultManager

        val documentsDir = fileManager
            .URLsForDirectory(NSDocumentDirectory, NSUserDomainMask)
            .firstOrNull() as? NSURL
            ?: run {
                logger.e("Documents directory not found")
                return
            }

        val fileName =
            downloadTask.response?.suggestedFilename
                ?: downloadTask.originalRequest?.URL?.lastPathComponent
                ?: "file.bin"

        val destinationUrl =
            documentsDir.URLByAppendingPathComponent(fileName)
                ?: run {
                    logger.e("Failed to create destination URL")
                    return
                }

        destinationUrl.path?.let {
            if (fileManager.fileExistsAtPath(it)) {
                logger.w("File exists, removing")
                fileManager.removeItemAtURL(destinationUrl, null)
            }
        }

        val success = fileManager.moveItemAtURL(
            srcURL = didFinishDownloadingToURL,
            toURL = destinationUrl,
            error = null
        )

        if (success) {
            logger.i("File saved at: ${destinationUrl.path}")
        } else {
            logger.e("Move file failed")
        }
    }

    override fun URLSession(
        session: NSURLSession,
        task: NSURLSessionTask,
        didCompleteWithError: NSError?
    ) {
        if (didCompleteWithError != null) {
            logger.e("Download error: ${didCompleteWithError.localizedDescription}")
        } else {
            logger.i("Task completed successfully")
        }
    }
}