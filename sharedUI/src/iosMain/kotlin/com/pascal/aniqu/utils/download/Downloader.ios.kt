package com.pascal.aniqu.utils.download

import kotlinx.cinterop.ExperimentalForeignApi
import platform.Foundation.NSDocumentDirectory
import platform.Foundation.NSFileManager
import platform.Foundation.NSURL
import platform.Foundation.NSURLRequest
import platform.Foundation.NSURLSession
import platform.Foundation.NSURLSessionConfiguration
import platform.Foundation.NSUserDomainMask
import platform.Foundation.downloadTaskWithRequest

actual class DownloadManager actual constructor() : Downloader {

    @OptIn(ExperimentalForeignApi::class)
    actual override fun download(url: String) {
        val nsUrl = NSURL.URLWithString(url) ?: return

        val request = NSURLRequest.requestWithURL(nsUrl)

        val session = NSURLSession.sessionWithConfiguration(
            NSURLSessionConfiguration.defaultSessionConfiguration()
        )

        val task = session.downloadTaskWithRequest(request) { tempUrl, response, error ->
            if (error != null || tempUrl == null) return@downloadTaskWithRequest

            val fileName =
                response?.suggestedFilename ?: nsUrl.lastPathComponent ?: "file.bin"

            val fileManager = NSFileManager.defaultManager

            val urls = fileManager.URLsForDirectory(
                directory = NSDocumentDirectory,
                inDomains = NSUserDomainMask
            )

            val documentsDir = urls[0] as? NSURL
                ?: return@downloadTaskWithRequest

            val destinationUrl = documentsDir.URLByAppendingPathComponent(fileName)

            destinationUrl?.path?.let { path ->
                if (fileManager.fileExistsAtPath(path)) {
                    fileManager.removeItemAtURL(destinationUrl, null)
                }
            }

            fileManager.moveItemAtURL(
                srcURL = tempUrl,
                toURL = destinationUrl!!,
                error = null
            )
        }

        task.resume()
    }
}