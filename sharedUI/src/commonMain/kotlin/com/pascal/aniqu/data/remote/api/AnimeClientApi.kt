package com.pascal.aniqu.data.remote.api

import com.pascal.aniqu.BuildKonfig
import com.pascal.aniqu.data.remote.dtos.BaseResponse
import com.pascal.aniqu.data.remote.dtos.anime.AnimeDetailResponse
import com.pascal.aniqu.data.remote.dtos.anime.AnimeGenreResponse
import com.pascal.aniqu.data.remote.dtos.anime.AnimeItemResponse
import com.pascal.aniqu.data.remote.dtos.anime.AnimeStreamingResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import org.koin.core.annotation.Single

@Single
class AnimeClientApi(
    private val client: HttpClient
) {
    suspend fun getAnimeHome(): BaseResponse<List<AnimeItemResponse>> {
        return client.get("${BuildKonfig.BASE_URL_ANIME}/home").body()
    }

    suspend fun getAnimeLive(page: Int): BaseResponse<List<AnimeItemResponse>> {
        return client.get("${BuildKonfig.BASE_URL_ANIME}/terbaru") {
            parameter("page", "$page")
        }.body()
    }

    suspend fun getAnimeDetail(slug: String): BaseResponse<AnimeDetailResponse> {
        return client.get("${BuildKonfig.BASE_URL_ANIME}/detail/$slug").body()
    }

    suspend fun getAnimeGenre(): BaseResponse<List<AnimeGenreResponse>> {
        return client.get("${BuildKonfig.BASE_URL_ANIME}/genres").body()
    }

    suspend fun getAnimeGenre(slug: String): BaseResponse<List<AnimeItemResponse>> {
        return client.get("${BuildKonfig.BASE_URL_ANIME}/genre/$slug").body()
    }

    suspend fun getAnimeSearch(key: String): BaseResponse<List<AnimeItemResponse>> {
        return client.get("${BuildKonfig.BASE_URL_ANIME}/search/$key").body()
    }

    suspend fun getAnimeStreaming(id: String): BaseResponse<AnimeStreamingResponse> {
        return client.get("${BuildKonfig.BASE_URL_ANIME}/episode/$id").body()
    }
}