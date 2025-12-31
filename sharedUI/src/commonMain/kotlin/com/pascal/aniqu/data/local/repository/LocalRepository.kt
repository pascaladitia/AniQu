package com.pascal.aniqu.data.local.repository

import com.pascal.aniqu.data.local.entity.FavoritesEntity
import com.pascal.aniqu.data.local.entity.ProfileEntity


interface LocalRepository {
    suspend fun getProfileById(id: Long): ProfileEntity?
    suspend fun getAllProfiles(): List<ProfileEntity>
    suspend fun deleteProfileById(item: ProfileEntity)
    suspend fun insertProfile(item: ProfileEntity)

    suspend fun insertFavorite(entity: FavoritesEntity)
    suspend fun deleteFavorite(entity: FavoritesEntity)
    suspend fun getFavorite(): List<FavoritesEntity>?
    suspend fun getFavorite(title: String): Boolean
    suspend fun clearFavorite()
}