package com.pascal.aniqu.domain.usecase.local

import com.pascal.aniqu.data.local.entity.FavoritesEntity
import com.pascal.aniqu.data.local.repository.LocalRepository
import com.pascal.aniqu.domain.mapper.favorite.toAnimeItem
import com.pascal.aniqu.domain.model.anime.AnimeItem
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import org.koin.core.annotation.Single

@Single
class LocalUseCaseImpl(
    private val repository: LocalRepository,
) : LocalUseCase {

    override suspend fun getFavorite(): Flow<List<AnimeItem>> = flow {
        emit(repository.getFavorite().orEmpty().map { it.toAnimeItem() })
    }

    override suspend fun getFavorite(slug: String): Flow<Boolean> = flow {
        emit(repository.getFavorite(slug))
    }

    override suspend fun insertFavorite(entity: FavoritesEntity) {
        return repository.insertFavorite(entity)
    }

    override suspend fun deleteFavorite(slug: String) {
        return repository.deleteFavorite(slug)
    }

    override suspend fun clearFavorite() {
        return repository.clearFavorite()
    }
}