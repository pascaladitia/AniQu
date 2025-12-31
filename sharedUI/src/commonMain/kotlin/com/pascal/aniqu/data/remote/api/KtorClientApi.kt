package com.pascal.aniqu.data.remote.api

import com.pascal.aniqu.data.remote.client
import com.pascal.aniqu.data.remote.dtos.*
import com.pascal.aniqu.data.remote.dtos.dashboard.DashboardResponse
import com.pascal.aniqu.utils.base.JsonReader
import io.ktor.client.call.body
import io.ktor.client.request.get
import org.koin.core.annotation.Single

@Single
class KtorClientApi {

    suspend fun dashboard(): DashboardResponse =
        client.get("http:///dashboard").body()

    suspend fun getMarketHighlight(): MarketHighlightResponse =
        JsonReader.load("market_highlight.json")

    suspend fun getStockRecommendation(): StockRecommendationResponse =
        JsonReader.load("stock_recommendation.json")
}
