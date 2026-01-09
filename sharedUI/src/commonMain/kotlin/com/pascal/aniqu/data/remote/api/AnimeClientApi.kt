package com.pascal.aniqu.data.remote.api

import com.pascal.aniqu.BuildKonfig
import com.pascal.aniqu.data.remote.client
import com.pascal.aniqu.data.remote.dtos.BaseResponse
import com.pascal.aniqu.data.remote.dtos.anime.AnimeDetailResponse
import com.pascal.aniqu.data.remote.dtos.anime.AnimeEpisodeDetailResponse
import com.pascal.aniqu.data.remote.dtos.anime.AnimeListResponse
import com.pascal.aniqu.data.remote.dtos.anime.AnimeResponse
import com.pascal.aniqu.data.remote.dtos.anime.AnimeSectionResponse
import com.pascal.aniqu.data.remote.dtos.anime.AnimeStreamingResponse
import com.pascal.aniqu.data.remote.dtos.anime.item.AnimeGenreResponse
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import org.koin.core.annotation.Single

@Single
class AnimeClientApi {

    suspend fun getAnimeHome(): BaseResponse<AnimeResponse> {
        return client.get("${BuildKonfig.BASE_URL}/home").body()
    }

    suspend fun getAnimeLive(page: Int): BaseResponse<AnimeSectionResponse> {
        return client.get("${BuildKonfig.BASE_URL}/ongoing-anime"){
            parameter("page", "$page")
        }.body()
    }

    suspend fun getAnimeDetail(slug: String): BaseResponse<AnimeDetailResponse>{
        return client.get("${BuildKonfig.BASE_URL}/anime/$slug").body()
    }

    suspend fun getAnimeGenre(): BaseResponse<AnimeGenreResponse>{
        return client.get("${BuildKonfig.BASE_URL}/genre").body()
    }

    suspend fun getAnimeGenre(slug: String): BaseResponse<AnimeListResponse> {
        return client.get("${BuildKonfig.BASE_URL}/genre/$slug").body()
    }

    suspend fun getAnimeSearch(key: String): BaseResponse<AnimeListResponse> {
        return client.get("${BuildKonfig.BASE_URL}/search/$key").body()
    }

    suspend fun getAnimeEpisode(slug: String): BaseResponse<AnimeEpisodeDetailResponse> {
        return client.get("${BuildKonfig.BASE_URL}/episode/$slug").body()
    }

    suspend fun getAnimeStreaming(id: String): BaseResponse<AnimeStreamingResponse> {
        return client.get("${BuildKonfig.BASE_URL}/server/$id").body()
    }
}
