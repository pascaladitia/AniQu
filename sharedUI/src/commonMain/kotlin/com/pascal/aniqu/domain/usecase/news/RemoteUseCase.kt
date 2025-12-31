package com.pascal.aniqu.domain.usecase.news

import com.pascal.aniqu.domain.model.Dashboard
import com.pascal.aniqu.domain.model.MarketHighlight
import com.pascal.aniqu.domain.model.StockRecommendation
import kotlinx.coroutines.flow.Flow

interface RemoteUseCase {
    suspend fun dashboard(): Flow<Dashboard>
    suspend fun getMarketHighlight(): Flow<MarketHighlight>
    suspend fun getStockRecommendation(): Flow<List<StockRecommendation>>
}
