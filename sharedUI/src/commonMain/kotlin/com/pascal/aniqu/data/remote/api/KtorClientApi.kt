package com.pascal.aniqu.data.remote.api

import com.pascal.aniqu.BuildKonfig
import com.pascal.aniqu.data.remote.client
import com.pascal.aniqu.data.remote.dtos.BaseResponse
import com.pascal.aniqu.data.remote.dtos.anime.AnimeDetailResponse
import com.pascal.aniqu.data.remote.dtos.anime.AnimeResponse
import com.pascal.aniqu.data.remote.dtos.anime.AnimeSectionResponse
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import org.koin.core.annotation.Single

@Single
class KtorClientApi {

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
}
