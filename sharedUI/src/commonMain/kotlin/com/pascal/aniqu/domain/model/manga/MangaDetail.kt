package com.pascal.aniqu.domain.model.manga

import com.pascal.aniqu.domain.model.manga.item.Chapter

data class MangaDetail(
    val creator: String,
    val success: Boolean,
    val details: MangaDetailData
)

data class MangaDetailData(
    val title: String,
    val alternative: String,
    val image: String,
    val rating: String,
    val synopsis: String,
    val info: MangaInfo,
    val genres: List<String>,
    val chapters: List<Chapter>
)

data class MangaInfo(
    val status: String,
    val type: String,
    val released: String,
    val author: String,
    val artist: String,
    val serialization: String,
    val postedBy: String,
    val postedOn: String,
    val updatedOn: String,
    val views: String
)