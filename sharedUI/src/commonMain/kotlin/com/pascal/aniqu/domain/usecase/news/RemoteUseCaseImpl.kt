package com.pascal.aniqu.domain.usecase.news

import com.pascal.aniqu.data.repository.NewsRepository
import com.pascal.aniqu.domain.mapper.toDomain
import com.pascal.aniqu.domain.model.Dashboard
import com.pascal.aniqu.domain.model.MarketHighlight
import com.pascal.aniqu.domain.model.StockRecommendation
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import org.koin.core.annotation.Single

@Single
class RemoteUseCaseImpl(
    private val repository: NewsRepository
) : RemoteUseCase {

    override suspend fun dashboard(): Flow<Dashboard> = flow {
        emit(repository.dashboard().toDomain())
    }

    override suspend fun getMarketHighlight(): Flow<MarketHighlight> = flow {
        emit(repository.getMarketHighlight().toDomain())
    }

    override suspend fun getStockRecommendation(): Flow<List<StockRecommendation>> = flow {
        emit(repository.getStockRecommendation().toDomain())
    }
}
