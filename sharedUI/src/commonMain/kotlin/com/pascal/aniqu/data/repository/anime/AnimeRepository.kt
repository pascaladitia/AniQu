package com.pascal.aniqu.data.repository.anime

import com.pascal.aniqu.data.remote.dtos.BaseResponse
import com.pascal.aniqu.data.remote.dtos.anime.AnimeDetailResponse
import com.pascal.aniqu.data.remote.dtos.anime.AnimeEpisodeDetailResponse
import com.pascal.aniqu.data.remote.dtos.anime.AnimeListResponse
import com.pascal.aniqu.data.remote.dtos.anime.AnimeResponse
import com.pascal.aniqu.data.remote.dtos.anime.AnimeSectionResponse
import com.pascal.aniqu.data.remote.dtos.anime.AnimeStreamingResponse
import com.pascal.aniqu.data.remote.dtos.anime.item.AnimeGenreResponse

interface AnimeRepository {
    suspend fun getAnimeHome() : BaseResponse<AnimeResponse>
    suspend fun getAnimeLive(page: Int): BaseResponse<AnimeSectionResponse>
    suspend fun getAnimeDetail(slug: String): BaseResponse<AnimeDetailResponse>
    suspend fun getAnimeGenre(): BaseResponse<AnimeGenreResponse>
    suspend fun getAnimeGenre(slug: String): BaseResponse<AnimeListResponse>
    suspend fun getAnimeSearch(key: String): BaseResponse<AnimeListResponse>
    suspend fun getAnimeEpisode(slug: String): BaseResponse<AnimeEpisodeDetailResponse>
    suspend fun getAnimeStreaming(id: String): BaseResponse<AnimeStreamingResponse>
}