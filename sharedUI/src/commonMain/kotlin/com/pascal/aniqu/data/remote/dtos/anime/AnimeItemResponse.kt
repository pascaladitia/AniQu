package com.pascal.aniqu.data.remote.dtos.anime

import kotlinx.serialization.Serializable

@Serializable
data class AnimeItemResponse(
    val title: String? = null,
    val slug: String? = null,
    val poster: String? = null,
    val episodes: String? = null,
    val type: String? = null,
    val status: String? = null,
    val date: String? = null
)