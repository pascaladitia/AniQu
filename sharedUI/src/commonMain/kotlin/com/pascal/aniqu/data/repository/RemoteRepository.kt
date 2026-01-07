package com.pascal.aniqu.data.repository

import com.pascal.aniqu.data.remote.dtos.BaseResponse
import com.pascal.aniqu.data.remote.dtos.anime.AnimeResponse
import com.pascal.aniqu.data.remote.dtos.anime.AnimeSectionResponse

interface RemoteRepository {
    suspend fun getAnimeHome() : BaseResponse<AnimeResponse>
    suspend fun getAnimeLive(page: Int): BaseResponse<AnimeSectionResponse>
}