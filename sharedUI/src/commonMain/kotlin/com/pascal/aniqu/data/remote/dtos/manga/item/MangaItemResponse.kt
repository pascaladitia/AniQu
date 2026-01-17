package com.pascal.aniqu.data.remote.dtos.manga.item

import kotlinx.serialization.Serializable

@Serializable
data class MangaItemResponse(
    val title: String? = null,
    val slug: String? = null,
    val image: String? = null,
    val latestChapter: String? = null,
    val rating: String? = null,
    val link: String? = null,
    val chapters: List<ChapterResponse?>? = null
)