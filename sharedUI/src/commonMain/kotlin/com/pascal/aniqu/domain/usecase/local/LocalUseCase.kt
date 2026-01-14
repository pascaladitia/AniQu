package com.pascal.aniqu.domain.usecase.local

import com.pascal.aniqu.data.local.entity.FavoritesEntity
import com.pascal.aniqu.domain.model.anime.AnimeItem
import kotlinx.coroutines.flow.Flow

interface LocalUseCase {
    suspend fun getFavorite(): Flow<List<AnimeItem>>
    suspend fun getFavorite(slug: String): Flow<Boolean>
    suspend fun insertFavorite(entity: FavoritesEntity): Flow<Unit>
    suspend fun deleteFavorite(slug: String): Flow<Unit>
    suspend fun clearFavorite(): Flow<Unit>
}