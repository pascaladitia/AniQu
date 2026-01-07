package com.pascal.aniqu.data.repository

import com.pascal.aniqu.data.remote.api.KtorClientApi
import com.pascal.aniqu.data.remote.dtos.BaseResponse
import com.pascal.aniqu.data.remote.dtos.anime.AnimeResponse
import com.pascal.aniqu.data.remote.dtos.anime.AnimeSectionResponse
import org.koin.core.annotation.Single

@Single
class RemoteRepositoryImpl(
    private val api: KtorClientApi
) : RemoteRepository {
    override suspend fun getAnimeHome(): BaseResponse<AnimeResponse> {
        return api.getAnimeHome()
    }

    override suspend fun getAnimeLive(page: Int): BaseResponse<AnimeSectionResponse> {
        return api.getAnimeLive(page)
    }
}