package com.pascal.aniqu.data.repository.anime

import com.pascal.aniqu.data.remote.dtos.BaseResponse
import com.pascal.aniqu.data.remote.dtos.anime.AnimeDetailResponse
import com.pascal.aniqu.data.remote.dtos.anime.AnimeGenreResponse
import com.pascal.aniqu.data.remote.dtos.anime.AnimeItemResponse
import com.pascal.aniqu.data.remote.dtos.anime.AnimeStreamingResponse

interface AnimeRepository {
    suspend fun getAnimeHome() : BaseResponse<List<AnimeItemResponse>>
    suspend fun getAnimeLive(page: Int): BaseResponse<List<AnimeItemResponse>>
    suspend fun getAnimeDetail(slug: String): BaseResponse<AnimeDetailResponse>
    suspend fun getAnimeGenre(): BaseResponse<List<AnimeGenreResponse>>
    suspend fun getAnimeGenre(slug: String): BaseResponse<List<AnimeItemResponse>>
    suspend fun getAnimeSearch(key: String): BaseResponse<List<AnimeItemResponse>>
    suspend fun getAnimeStreaming(id: String): BaseResponse<AnimeStreamingResponse>
}