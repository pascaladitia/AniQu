package com.pascal.aniqu.domain.model.anime

import kotlinx.serialization.Serializable

data class AnimeStreaming(
    val title: String,
    val poster: String,
    val date: String,
    val synopsis: String,
    val genres: List<String>,

    val navigation: Navigation,
    val streams: List<Stream>,
    val downloads: List<Download>
)

data class Navigation(
    val prevSlug: String,
    val nextSlug: String,
    val allEpisodesSlug: String
)

@Serializable
data class Stream(
    val server: String,
    val url: String,
    val isEmbed: Boolean
)

data class Download(
    val resolution: String,
    val server: String,
    val url: String
)