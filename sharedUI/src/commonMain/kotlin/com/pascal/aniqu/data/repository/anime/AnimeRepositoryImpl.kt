package com.pascal.aniqu.data.repository.anime

import com.pascal.aniqu.data.remote.api.KtorClientApi
import com.pascal.aniqu.data.remote.dtos.BaseResponse
import com.pascal.aniqu.data.remote.dtos.anime.AnimeDetailResponse
import com.pascal.aniqu.data.remote.dtos.anime.AnimeResponse
import com.pascal.aniqu.data.remote.dtos.anime.AnimeSectionResponse
import org.koin.core.annotation.Single

@Single
class AnimeRepositoryImpl(
    private val api: KtorClientApi
) : AnimeRepository {
    override suspend fun getAnimeHome(): BaseResponse<AnimeResponse> {
        return api.getAnimeHome()
    }

    override suspend fun getAnimeLive(page: Int): BaseResponse<AnimeSectionResponse> {
        return api.getAnimeLive(page)
    }

    override suspend fun getAnimeDetail(slug: String): BaseResponse<AnimeDetailResponse> {
        return api.getAnimeDetail(slug)
    }
}