package com.pascal.aniqu.domain.mapper

import com.pascal.aniqu.data.remote.dtos.anime.AnimeStreamingResponse
import com.pascal.aniqu.data.remote.dtos.anime.DownloadResponse
import com.pascal.aniqu.data.remote.dtos.anime.NavigationResponse
import com.pascal.aniqu.data.remote.dtos.anime.StreamResponse
import com.pascal.aniqu.domain.model.anime.AnimeStreaming
import com.pascal.aniqu.domain.model.anime.Download
import com.pascal.aniqu.domain.model.anime.Navigation
import com.pascal.aniqu.domain.model.anime.Stream

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
    url = url.orEmpty()
)

fun DownloadResponse.toDomain() = Download(
    resolution = resolution.orEmpty(),
    server = server.orEmpty(),
    url = url.orEmpty()
)