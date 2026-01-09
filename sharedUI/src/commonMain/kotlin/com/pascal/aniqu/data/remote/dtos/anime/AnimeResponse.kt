package com.pascal.aniqu.data.remote.dtos.anime

import com.pascal.aniqu.data.remote.dtos.anime.item.AnimeItemResponse
import kotlinx.serialization.Serializable

@Serializable
data class AnimeResponse(
    val ongoing: AnimeSectionResponse? = null,
    val completed: AnimeSectionResponse? = null
)

@Serializable
data class AnimeSectionResponse(
    val href: String? = null,
    val otakudesuUrl: String? = null,
    val animeList: List<AnimeItemResponse> = emptyList()
)