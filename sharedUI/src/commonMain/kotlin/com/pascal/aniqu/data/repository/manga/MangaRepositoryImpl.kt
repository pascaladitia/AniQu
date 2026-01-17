package com.pascal.aniqu.data.repository.manga

import com.pascal.aniqu.data.remote.api.MangaClientApi
import com.pascal.aniqu.data.remote.dtos.manga.MangaDetailResponse
import com.pascal.aniqu.data.remote.dtos.manga.MangaResponse
import org.koin.core.annotation.Single

@Single
class MangaRepositoryImpl(
    private val api: MangaClientApi
) : MangaRepository {
    override suspend fun getMangaHome(): MangaResponse {
        return api.getMangaHome()
    }

    override suspend fun getMangaDetail(slug: String): MangaDetailResponse {
        return api.getMangaDetail(slug)
    }

}