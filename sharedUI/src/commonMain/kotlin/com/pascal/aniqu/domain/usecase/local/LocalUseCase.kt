package com.pascal.aniqu.domain.usecase.local

import com.pascal.aniqu.data.local.entity.FavoritesEntity
import com.pascal.aniqu.domain.model.anime.AnimeItem
import kotlinx.coroutines.flow.Flow

interface LocalUseCase {
    suspend fun insertFavorite(entity: FavoritesEntity): Flow<Unit>
    suspend fun deleteFavorite(entity: FavoritesEntity): Flow<Unit>
    suspend fun getFavorite(): Flow<List<AnimeItem>>
    suspend fun getFavorite(title: String): Flow<Boolean>
    suspend fun clearFavorite(): Flow<Unit>
}