package com.pascal.aniqu.domain.usecase.news

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.pascal.aniqu.data.remote.api.KtorClientApi
import com.pascal.aniqu.data.repository.NewsRepository
import com.pascal.aniqu.domain.mapper.toDomain
import com.pascal.aniqu.domain.model.Anime
import com.pascal.aniqu.domain.model.Dashboard
import com.pascal.aniqu.domain.model.MarketHighlight
import com.pascal.aniqu.domain.model.StockRecommendation
import com.pascal.aniqu.domain.usecase.pagination.AnimePagingSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import org.koin.core.annotation.Single

@Single
class RemoteUseCaseImpl(
    private val api: KtorClientApi,
    private val repository: NewsRepository
) : RemoteUseCase {

    override suspend fun dashboard(): Flow<Dashboard> = flow {
        emit(repository.dashboard().toDomain())
    }

    override suspend fun getAnimeList(): Flow<PagingData<Anime>> {
        return Pager(
            config = PagingConfig(pageSize = 1),
            pagingSourceFactory = {
                AnimePagingSource(api)
            }
        ).flow
    }

    override suspend fun getMarketHighlight(): Flow<MarketHighlight> = flow {
        emit(repository.getMarketHighlight().toDomain())
    }

    override suspend fun getStockRecommendation(): Flow<List<StockRecommendation>> = flow {
        emit(repository.getStockRecommendation().toDomain())
    }
}
