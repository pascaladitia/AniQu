package com.pascal.aniqu.domain.model.manga

import com.pascal.aniqu.domain.model.manga.item.MangaItem

data class Manga(
    val creator: String,
    val success: Boolean,
    val popularToday: List<MangaItem>,
    val projectUpdates: List<MangaItem>,
    val latestReleases: List<MangaItem>
)