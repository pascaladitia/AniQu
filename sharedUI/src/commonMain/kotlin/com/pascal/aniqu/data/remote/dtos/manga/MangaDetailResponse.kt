package com.pascal.aniqu.data.remote.dtos.manga

import com.pascal.aniqu.data.remote.dtos.manga.item.ChapterResponse
import kotlinx.serialization.Serializable

@Serializable
data class MangaDetailResponse(
    val creator: String? = null,
    val success: Boolean? = null,
    val details: MangaDetailDataResponse? = null
)

@Serializable
data class MangaDetailDataResponse(
    val title: String? = null,
    val alternative: String? = null,
    val image: String? = null,
    val rating: String? = null,
    val synopsis: String? = null,
    val info: MangaInfoResponse? = null,
    val genres: List<String?>? = null,
    val chapters: List<ChapterResponse?>? = null
)

@Serializable
data class MangaInfoResponse(
    val status: String? = null,
    val type: String? = null,
    val released: String? = null,
    val author: String? = null,
    val artist: String? = null,
    val serialization: String? = null,
    val posted_by: String? = null,
    val posted_on: String? = null,
    val updated_on: String? = null,
    val views: String? = null
)