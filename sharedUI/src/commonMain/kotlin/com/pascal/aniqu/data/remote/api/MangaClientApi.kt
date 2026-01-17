package com.pascal.aniqu.data.remote.api

import com.pascal.aniqu.BuildKonfig
import com.pascal.aniqu.data.remote.dtos.manga.MangaDetailResponse
import com.pascal.aniqu.data.remote.dtos.manga.MangaResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import org.koin.core.annotation.Single

@Single
class MangaClientApi(
    private val client: HttpClient
) {
    suspend fun getMangaHome(): MangaResponse {
        return client.get("${BuildKonfig.BASE_URL_MANGA}/home").body()
    }

    suspend fun getMangaDetail(slug: String): MangaDetailResponse {
        return client.get("${BuildKonfig.BASE_URL_MANGA}/detail/$slug").body()
    }
}