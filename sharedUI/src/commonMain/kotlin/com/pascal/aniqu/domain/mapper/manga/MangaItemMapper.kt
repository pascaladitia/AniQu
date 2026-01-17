package com.pascal.aniqu.domain.mapper.manga

import com.pascal.aniqu.data.remote.dtos.manga.item.ChapterResponse
import com.pascal.aniqu.data.remote.dtos.manga.item.MangaItemResponse
import com.pascal.aniqu.domain.model.manga.item.Chapter
import com.pascal.aniqu.domain.model.manga.item.MangaItem

fun MangaItemResponse.toDomain(): MangaItem =
    MangaItem(
        title = title.orEmpty(),
        slug = slug.orEmpty(),
        image = image.orEmpty(),
        latestChapter = latestChapter.orEmpty(),
        rating = rating.orEmpty(),
        link = link.orEmpty(),
        chapters = chapters.orEmpty()
            .filterNotNull()
            .map { it.toDomain() }
    )

fun ChapterResponse.toDomain(): Chapter =
    Chapter(
        title = title.orEmpty(),
        slug = slug.orEmpty(),
        time = time.orEmpty()
    )