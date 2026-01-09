package com.pascal.aniqu.domain.usecase.anime

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.pascal.aniqu.data.repository.anime.AnimeRepository
import com.pascal.aniqu.domain.mapper.toDomain
import com.pascal.aniqu.domain.model.Anime
import com.pascal.aniqu.domain.model.AnimeDetail
import com.pascal.aniqu.domain.model.AnimeItem
import com.pascal.aniqu.domain.usecase.pagination.AnimePagingSource
import com.pascal.aniqu.utils.base.safeApiCall
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import org.koin.core.annotation.Single

@Single
class AnimeUseCaseImpl(
    private val repository: AnimeRepository
) : AnimeUseCase {

    override suspend fun getAnimeHome(): Flow<Anime> = flow {
        emit(safeApiCall({ repository.getAnimeHome() }) { toDomain() })
    }

    override suspend fun getAnimeLive(): Flow<PagingData<AnimeItem>> {
        return Pager(
            config = PagingConfig(pageSize = 1),
            pagingSourceFactory = {
                AnimePagingSource(repository)
            }
        ).flow
    }

    override suspend fun getAnimeDetail(slug: String): Flow<AnimeDetail> = flow {
        emit(safeApiCall({ repository.getAnimeDetail(slug) }) { toDomain() })
    }
}
