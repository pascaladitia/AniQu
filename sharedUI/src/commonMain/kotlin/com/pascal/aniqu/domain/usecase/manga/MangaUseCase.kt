package com.pascal.aniqu.domain.usecase.manga

import com.pascal.aniqu.domain.model.manga.Manga
import com.pascal.aniqu.domain.model.manga.MangaDetail
import kotlinx.coroutines.flow.Flow

interface MangaUseCase {
    suspend fun getMangaHome(): Flow<Manga>
    suspend fun getMangaDetail(slug: String): Flow<MangaDetail>
}
