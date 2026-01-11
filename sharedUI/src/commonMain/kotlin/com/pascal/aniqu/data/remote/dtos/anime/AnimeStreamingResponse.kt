package com.pascal.aniqu.data.remote.dtos.anime

import kotlinx.serialization.Serializable

@Serializable
data class AnimeStreamingResponse(
    val title: String? = null,
    val poster: String? = null,
    val date: String? = null,
    val synopsis: String? = null,
    val genres: List<String>? = null,
    val navigation: NavigationResponse? = null,
    val streams: List<StreamResponse>? = null,
    val downloads: List<DownloadResponse>? = null
)

@Serializable
data class NavigationResponse(
    val prev_slug: String? = null,
    val next_slug: String? = null,
    val all_episodes_slug: String? = null
)

@Serializable
data class StreamResponse(
    val server: String? = null,
    val url: String? = null
)

@Serializable
data class DownloadResponse(
    val resolution: String? = null,
    val server: String? = null,
    val url: String? = null
)
