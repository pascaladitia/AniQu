package com.pascal.aniqu.data.remote.api

import com.pascal.aniqu.BuildKonfig
import com.pascal.aniqu.data.remote.client
import com.pascal.aniqu.data.remote.dtos.BaseResponse
import com.pascal.aniqu.data.remote.dtos.dashboard.AnimeResponse
import com.pascal.aniqu.data.remote.dtos.home.AnimeHomeResponse
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import org.koin.core.annotation.Single

@Single
class KtorClientApi {

    suspend fun getAnimeHome(): BaseResponse<AnimeHomeResponse> {
        return client.get("${BuildKonfig.BASE_URL}/home").body()
    }

    suspend fun getAnimeList(page: Int): AnimeResponse {
        return client.get("${BuildKonfig.BASE_URL}/anime"){
            parameter("page[limit]", "5")
            parameter("page[offset]", "$page")
        }.body()
    }
}
