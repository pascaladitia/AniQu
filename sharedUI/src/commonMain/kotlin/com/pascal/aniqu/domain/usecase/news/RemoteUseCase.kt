package com.pascal.aniqu.domain.usecase.news

import androidx.paging.PagingData
import com.pascal.aniqu.domain.model.Anime
import com.pascal.aniqu.domain.model.Dashboard
import com.pascal.aniqu.domain.model.MarketHighlight
import com.pascal.aniqu.domain.model.StockRecommendation
import kotlinx.coroutines.flow.Flow

interface RemoteUseCase {
    suspend fun dashboard(): Flow<Dashboard>
    suspend fun getAnimeList(): Flow<PagingData<Anime>>
    suspend fun getMarketHighlight(): Flow<MarketHighlight>
    suspend fun getStockRecommendation(): Flow<List<StockRecommendation>>
}
