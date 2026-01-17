package com.pascal.aniqu.domain.model.manga.item


data class MangaItem(
    val title: String,
    val slug: String,
    val image: String,
    val latestChapter: String,
    val rating: String,
    val link: String,
    val chapters: List<Chapter>
)