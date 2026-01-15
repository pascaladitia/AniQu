package com.pascal.aniqu.utils.download

actual class DownloadManager actual constructor() : Downloader {

    private val systemDownloader = SystemDownloadManager()

    actual override fun download(url: String) {
        systemDownloader.download(url)
    }
}