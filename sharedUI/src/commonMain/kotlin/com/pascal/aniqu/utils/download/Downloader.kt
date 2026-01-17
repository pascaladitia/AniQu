package com.pascal.aniqu.utils.download

interface Downloader {
    fun download(url: String)
}

expect class DownloadManager() : Downloader {
    override fun download(url: String)
}
