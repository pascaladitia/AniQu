package com.pascal.aniqu.data.remote.api

import com.pascal.aniqu.BuildKonfig
import com.pascal.aniqu.data.remote.client
import com.pascal.aniqu.data.remote.dtos.*
import com.pascal.aniqu.data.remote.dtos.dashboard.AnimeResponse
import com.pascal.aniqu.data.remote.dtos.dashboard.DashboardResponse
import com.pascal.aniqu.utils.base.JsonReader
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import org.koin.core.annotation.Single

@Single
object KtorClientApi {

    suspend fun dashboard(): DashboardResponse =
        client.get("http:///dashboard").body()

    suspend fun getAnimeList(page: Int): AnimeResponse {
        return client.get("${BuildKonfig.BASE_URL}/anime"){
            parameter("page[limit]", "10")
            parameter("page[offset]", "$page")
        }.body()
    }

    suspend fun getMarketHighlight(): MarketHighlightResponse =
        JsonReader.load("market_highlight.json")

    suspend fun getStockRecommendation(): StockRecommendationResponse =
        JsonReader.load("stock_recommendation.json")
}
