package com.pascal.aniqu.data.remote.dtos.anime

import kotlinx.serialization.Serializable

@Serializable
data class AnimeStreamingResponse(
    val url: String? = null
)