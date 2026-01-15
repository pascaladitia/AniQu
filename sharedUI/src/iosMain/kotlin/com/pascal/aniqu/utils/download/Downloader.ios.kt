package com.pascal.aniqu.utils.download

actual class DownloadManager actual constructor() : Downloader {
    actual override fun download(url: String) {
        IOSBackgroundDownloader.download(url)
    }
}