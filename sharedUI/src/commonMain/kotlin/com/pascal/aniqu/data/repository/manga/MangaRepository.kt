package com.pascal.aniqu.data.repository.manga

import com.pascal.aniqu.data.remote.dtos.manga.MangaDetailResponse
import com.pascal.aniqu.data.remote.dtos.manga.MangaResponse

interface MangaRepository {
    suspend fun getMangaHome() : MangaResponse
    suspend fun getMangaDetail(slug: String): MangaDetailResponse
}