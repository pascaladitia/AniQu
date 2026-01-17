package com.pascal.aniqu.domain.mapper.anime

import com.pascal.aniqu.data.remote.dtos.anime.AnimeStreamingResponse
import com.pascal.aniqu.data.remote.dtos.anime.DownloadResponse
import com.pascal.aniqu.data.remote.dtos.anime.NavigationResponse
import com.pascal.aniqu.data.remote.dtos.anime.StreamResponse
import com.pascal.aniqu.domain.model.anime.AnimeStreaming
import com.pascal.aniqu.domain.model.anime.Download
import com.pascal.aniqu.domain.model.anime.Navigation
import com.pascal.aniqu.domain.model.anime.Stream
import com.pascal.aniqu.utils.extractResolution

fun AnimeStreamingResponse.toDomain(): AnimeStreaming {
    return AnimeStreaming(
        title = title.orEmpty(),
        poster = poster.orEmpty(),
        date = date.orEmpty(),
        synopsis = synopsis.orEmpty(),
        genres = genres.orEmpty(),

        navigation = navigation?.toDomain() ?: Navigation(
            prevSlug = "",
            nextSlug = "",
            allEpisodesSlug = ""
        ),

        streams = streams.orEmpty().map { it.toDomain() },
        downloads = downloads.orEmpty().map { it.toDomain() }
    )
}

fun NavigationResponse.toDomain() = Navigation(
    prevSlug = prev_slug.orEmpty(),
    nextSlug = next_slug.orEmpty(),
    allEpisodesSlug = all_episodes_slug.orEmpty()
)

fun StreamResponse.toDomain() = Stream(
    server = server.orEmpty(),
    url = url.orEmpty(),
    isEmbed = false
)

fun DownloadResponse.toDomain() = Download(
    resolution = resolution.orEmpty(),
    server = server.orEmpty(),
    url = url.orEmpty()
)

fun mapToStreamList(
    downloads: List<Download>?,
    streams: List<Stream>?
): List<Stream> {
    val mp4List = downloads
        ?.filterMp4UniqueByResolution()
        .orEmpty()

    return if (mp4List.isNotEmpty()) {
        mp4List.map {
            Stream(
                server = it.resolution,
                url = it.url,
                isEmbed = false
            )
        }
    } else {
        streams
            ?.filterStreamingByServer()
            ?.map {
                Stream(
                    server = it.server,
                    url = it.url,
                    isEmbed = true
                )
            }
            .orEmpty()
    }
}

fun List<Download>.filterMp4UniqueByResolution(): List<Download> {
    return this
        .filter { it.url.endsWith(".mp4", ignoreCase = true) }
        .distinctBy { it.resolution }
}

fun List<Stream>.filterStreamingByServer(): List<Stream> {
    return this
        .distinctBy { it.server.extractResolution() }
        .filter { it.server.isNotBlank() }
}