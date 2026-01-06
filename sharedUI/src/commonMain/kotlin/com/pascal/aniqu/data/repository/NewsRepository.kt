package com.pascal.aniqu.data.repository

import com.pascal.aniqu.data.remote.dtos.BaseResponse
import com.pascal.aniqu.data.remote.dtos.dashboard.AnimeResponse
import com.pascal.aniqu.data.remote.dtos.home.AnimeHomeResponse

interface NewsRepository {
    suspend fun getAnimeHome() : BaseResponse<AnimeHomeResponse>
    suspend fun getAnimeList(page: Int): AnimeResponse
}