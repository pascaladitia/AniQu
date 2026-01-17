package com.pascal.aniqu.utils.download

import co.touchlab.kermit.Logger
import com.pascal.aniqu.utils.downloadDirectory
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.statement.HttpResponse
import io.ktor.http.contentLength
import io.ktor.http.isSuccess
import io.ktor.utils.io.ByteReadChannel
import io.ktor.utils.io.readAvailable
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okio.FileSystem
import okio.Path.Companion.toPath
import okio.buffer

sealed interface DownloadState {
    data class Progress(val percent: Int) : DownloadState
    data class Success(val filePath: String) : DownloadState
    data class Error(val message: String) : DownloadState
}

class KtorDownloader(
    private val client: HttpClient,
    private val fileSystem: FileSystem
) {
    private val log = Logger.withTag("DownloadVM")
    private val baseDir: String = downloadDirectory()

    fun download(url: String): Flow<DownloadState> = flow {
        try {
            log.i { "Start download: $url" }

            val response: HttpResponse = client.get(url)
            if (!response.status.isSuccess()) {
                emit(DownloadState.Error("HTTP ${response.status.value}"))
                return@flow
            }

            val channel: ByteReadChannel = response.body()
            val total = response.contentLength() ?: -1L

            val path = "$baseDir/${generateFileName(url)}".toPath()
            val sink = fileSystem.sink(path).buffer()

            val buffer = ByteArray(8 * 1024)
            var downloaded = 0L

            while (!channel.isClosedForRead) {
                val read = channel.readAvailable(buffer)
                if (read == -1) break

                sink.write(buffer, 0, read)
                downloaded += read

                if (total > 0) {
                    val percent = ((downloaded * 100) / total).toInt()
                    emit(DownloadState.Progress(percent))
                    log.v { "Progress: $percent%" }
                }
            }

            sink.flush()
            sink.close()

            log.i { "Download Success: $path" }
            emit(DownloadState.Success(path.toString()))

        } catch (e: Exception) {
            log.e(e) { "Download failed" }
            emit(DownloadState.Error(e.message ?: "Unknown error"))
        }
    }

    private fun generateFileName(url: String): String =
        url.removePrefix("https://")
            .removePrefix("http://")
            .substringAfterLast("/")
            .replace(Regex("[^A-Za-z0-9._-]"), "_")
}
