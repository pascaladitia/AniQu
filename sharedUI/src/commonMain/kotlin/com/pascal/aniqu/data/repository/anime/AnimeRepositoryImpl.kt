package com.pascal.aniqu.data.repository.anime

import com.pascal.aniqu.data.remote.api.AnimeClientApi
import com.pascal.aniqu.data.remote.dtos.BaseResponse
import com.pascal.aniqu.data.remote.dtos.anime.AnimeDetailResponse
import com.pascal.aniqu.data.remote.dtos.anime.AnimeEpisodeDetailResponse
import com.pascal.aniqu.data.remote.dtos.anime.AnimeGenreResponse
import com.pascal.aniqu.data.remote.dtos.anime.AnimeItemResponse
import com.pascal.aniqu.data.remote.dtos.anime.AnimeStreamingResponse
import org.koin.core.annotation.Single

@Single
class AnimeRepositoryImpl(
    private val api: AnimeClientApi
) : AnimeRepository {
    override suspend fun getAnimeHome(): BaseResponse<List<AnimeItemResponse>> {
        return api.getAnimeHome()
    }

    override suspend fun getAnimeLive(page: Int): BaseResponse<List<AnimeItemResponse>> {
        return api.getAnimeLive(page)
    }

    override suspend fun getAnimeDetail(slug: String): BaseResponse<AnimeDetailResponse> {
        return api.getAnimeDetail(slug)
    }

    override suspend fun getAnimeGenre(): BaseResponse<List<AnimeGenreResponse>> {
        return api.getAnimeGenre()
    }

    override suspend fun getAnimeGenre(slug: String): BaseResponse<List<AnimeItemResponse>> {
        return api.getAnimeGenre(slug)
    }

    override suspend fun getAnimeSearch(key: String): BaseResponse<List<AnimeItemResponse>> {
        return api.getAnimeSearch(key)
    }

    override suspend fun getAnimeEpisode(slug: String): BaseResponse<AnimeEpisodeDetailResponse> {
        return api.getAnimeEpisode(slug)
    }

    override suspend fun getAnimeStreaming(id: String): BaseResponse<AnimeStreamingResponse> {
        return api.getAnimeStreaming(id)
    }
}