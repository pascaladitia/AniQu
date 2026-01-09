package com.pascal.aniqu.data.remote.dtos.anime

import com.pascal.aniqu.data.remote.dtos.anime.item.AnimeItemResponse
import kotlinx.serialization.Serializable

@Serializable
data class AnimeListResponse(
    val animeList: List<AnimeItemResponse>? = null
)