package com.pascal.aniqu.data.remote.dtos.anime

import kotlinx.serialization.Serializable

@Serializable
data class AnimeGenreResponse(
    val name: String? = null,
    val slug: String? = null,
    val count: String? = null,
)