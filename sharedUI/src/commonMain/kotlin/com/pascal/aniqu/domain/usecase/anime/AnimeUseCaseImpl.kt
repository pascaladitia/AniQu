package com.pascal.aniqu.domain.usecase.anime

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.pascal.aniqu.data.repository.anime.AnimeRepository
import com.pascal.aniqu.domain.mapper.toDomain
import com.pascal.aniqu.domain.model.AnimeDetail
import com.pascal.aniqu.domain.model.AnimeEpisodeDetail
import com.pascal.aniqu.domain.model.AnimeStreaming
import com.pascal.aniqu.domain.model.anime.AnimeItem
import com.pascal.aniqu.domain.model.anime.AnimeGenre
import com.pascal.aniqu.domain.usecase.pagination.AnimePagingSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import org.koin.core.annotation.Single

@Single
class AnimeUseCaseImpl(
    private val repository: AnimeRepository
) : AnimeUseCase {

    override suspend fun getAnimeHome(): Flow<List<AnimeItem>> = flow {
        emit(repository.getAnimeHome().data?.map { it.toDomain() } ?: emptyList())
    }

    override suspend fun getAnimeLive(): Flow<PagingData<AnimeItem>> {
        return Pager(
            config = PagingConfig(pageSize = 1),
            pagingSourceFactory = {
                AnimePagingSource(repository)
            }
        ).flow
    }

    override suspend fun getAnimeDetail(slug: String): Flow<AnimeDetail?> = flow {
        emit(repository.getAnimeDetail(slug).data?.toDomain())
    }

    override suspend fun getAnimeGenre(): Flow<List<AnimeGenre>> = flow {
        emit(repository.getAnimeGenre().data?.map { it.toDomain() } ?: emptyList())
    }

    override suspend fun getAnimeGenre(slug: String): Flow<List<AnimeItem>> = flow {
        emit(repository.getAnimeGenre(slug).data?.map { it.toDomain() } ?: emptyList())
    }

    override suspend fun getAnimeSearch(key: String): Flow<List<AnimeItem>> = flow {
        emit(repository.getAnimeSearch(key).data?.map { it.toDomain() } ?: emptyList())
    }

    override suspend fun getAnimeEpisode(slug: String): Flow<AnimeEpisodeDetail?> = flow {
        emit(repository.getAnimeEpisode(slug).data?.toDomain())
    }

    override suspend fun getAnimeStreaming(id: String): Flow<AnimeStreaming?> = flow {
        emit(repository.getAnimeStreaming(id).data?.toDomain())
    }
}
