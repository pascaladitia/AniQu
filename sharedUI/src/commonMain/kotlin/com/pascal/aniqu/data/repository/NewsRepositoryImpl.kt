package com.pascal.aniqu.data.repository

import com.pascal.aniqu.data.remote.api.KtorClientApi
import com.pascal.aniqu.data.remote.dtos.MarketHighlightResponse
import com.pascal.aniqu.data.remote.dtos.StockRecommendationResponse
import com.pascal.aniqu.data.remote.dtos.dashboard.AnimeResponse
import com.pascal.aniqu.data.remote.dtos.dashboard.DashboardResponse
import org.koin.core.annotation.Single

@Single
class NewsRepositoryImpl(
    private val api: KtorClientApi
) : NewsRepository {

    override suspend fun dashboard(): DashboardResponse {
        return api.dashboard()
    }

    override suspend fun getAnimeList(page: Int): AnimeResponse {
        return api.getAnimeList(page)
    }

    override suspend fun getMarketHighlight(): MarketHighlightResponse {
        return api.getMarketHighlight()
    }

    override suspend fun getStockRecommendation(): StockRecommendationResponse {
        return api.getStockRecommendation()
    }
}