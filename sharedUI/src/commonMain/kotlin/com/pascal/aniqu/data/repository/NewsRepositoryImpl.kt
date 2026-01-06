package com.pascal.aniqu.data.repository

import com.pascal.aniqu.data.remote.api.KtorClientApi
import com.pascal.aniqu.data.remote.dtos.BaseResponse
import com.pascal.aniqu.data.remote.dtos.dashboard.AnimeResponse
import com.pascal.aniqu.data.remote.dtos.home.AnimeHomeResponse
import org.koin.core.annotation.Single

@Single
class NewsRepositoryImpl(
    private val api: KtorClientApi
) : NewsRepository {
    override suspend fun getAnimeHome(): BaseResponse<AnimeHomeResponse> {
        return api.getAnimeHome()
    }

    override suspend fun getAnimeList(page: Int): AnimeResponse {
        return api.getAnimeList(page)
    }
}