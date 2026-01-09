package com.pascal.aniqu.data.repository.anime

import com.pascal.aniqu.data.remote.api.AnimeClientApi
import com.pascal.aniqu.data.remote.dtos.BaseResponse
import com.pascal.aniqu.data.remote.dtos.anime.AnimeDetailResponse
import com.pascal.aniqu.data.remote.dtos.anime.AnimeEpisodeDetailResponse
import com.pascal.aniqu.data.remote.dtos.anime.item.AnimeGenreResponse
import com.pascal.aniqu.data.remote.dtos.anime.AnimeListResponse
import com.pascal.aniqu.data.remote.dtos.anime.AnimeResponse
import com.pascal.aniqu.data.remote.dtos.anime.AnimeSectionResponse
import com.pascal.aniqu.data.remote.dtos.anime.AnimeStreamingResponse
import org.koin.core.annotation.Single

@Single
class AnimeRepositoryImpl(
    private val api: AnimeClientApi
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

    override suspend fun getAnimeGenre(): BaseResponse<AnimeGenreResponse> {
        return api.getAnimeGenre()
    }

    override suspend fun getAnimeGenre(slug: String): BaseResponse<AnimeListResponse> {
        return api.getAnimeGenre(slug)
    }

    override suspend fun getAnimeSearch(key: String): BaseResponse<AnimeListResponse> {
        return api.getAnimeSearch(key)
    }

    override suspend fun getAnimeEpisode(slug: String): BaseResponse<AnimeEpisodeDetailResponse> {
        return api.getAnimeEpisode(slug)
    }

    override suspend fun getAnimeStreaming(id: String): BaseResponse<AnimeStreamingResponse> {
        return api.getAnimeStreaming(id)
    }
}