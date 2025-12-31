package com.pascal.aniqu.data.repository

import com.pascal.aniqu.data.remote.dtos.MarketHighlightResponse
import com.pascal.aniqu.data.remote.dtos.StockRecommendationResponse
import com.pascal.aniqu.data.remote.dtos.dashboard.AnimeResponse
import com.pascal.aniqu.data.remote.dtos.dashboard.DashboardResponse

interface NewsRepository {
    suspend fun dashboard() : DashboardResponse
    suspend fun getAnimeList(page: Int): AnimeResponse
    suspend fun getMarketHighlight(): MarketHighlightResponse
    suspend fun getStockRecommendation(): StockRecommendationResponse
}