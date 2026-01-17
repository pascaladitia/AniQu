package com.pascal.aniqu.data.remote.dtos.manga.item

import kotlinx.serialization.Serializable

@Serializable
data class ChapterResponse(
    val title: String? = null,
    val slug: String? = null,
    val time: String? = null
)