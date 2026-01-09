package com.pascal.aniqu.data.repository.anime

import com.pascal.aniqu.data.remote.dtos.BaseResponse
import com.pascal.aniqu.data.remote.dtos.anime.AnimeDetailResponse
import com.pascal.aniqu.data.remote.dtos.anime.AnimeResponse
import com.pascal.aniqu.data.remote.dtos.anime.AnimeSectionResponse

interface AnimeRepository {
    suspend fun getAnimeHome() : BaseResponse<AnimeResponse>
    suspend fun getAnimeLive(page: Int): BaseResponse<AnimeSectionResponse>
    suspend fun getAnimeDetail(slug: String): BaseResponse<AnimeDetailResponse>
}