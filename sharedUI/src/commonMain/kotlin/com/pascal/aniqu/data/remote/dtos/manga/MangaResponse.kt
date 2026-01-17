package com.pascal.aniqu.data.remote.dtos.manga

import com.pascal.aniqu.data.remote.dtos.manga.item.MangaItemResponse
import kotlinx.serialization.Serializable

@Serializable
data class MangaResponse(
    val creator: String? = null,
    val success: Boolean? = null,
    val popularToday: List<MangaItemResponse?>? = null,
    val projectUpdates: List<MangaItemResponse?>? = null,
    val latestReleases: List<MangaItemResponse?>? = null
)
