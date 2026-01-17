package com.pascal.aniqu.domain.mapper.manga

import com.pascal.aniqu.data.remote.dtos.manga.MangaResponse
import com.pascal.aniqu.domain.model.manga.Manga

fun MangaResponse.toDomain(): Manga =
    Manga(
        creator = creator.orEmpty(),
        success = success ?: false,
        popularToday = popularToday.orEmpty()
            .filterNotNull()
            .map { it.toDomain() },
        projectUpdates = projectUpdates.orEmpty()
            .filterNotNull()
            .map { it.toDomain() },
        latestReleases = latestReleases.orEmpty()
            .filterNotNull()
            .map { it.toDomain() }
    )