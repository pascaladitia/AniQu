package com.pascal.aniqu.data.local.repository

import com.pascal.aniqu.data.local.entity.FavoritesEntity
import com.pascal.aniqu.data.local.entity.ProfileEntity


interface LocalRepository {
    suspend fun getProfileById(id: Long): ProfileEntity?
    suspend fun getAllProfiles(): List<ProfileEntity>
    suspend fun insertProfile(item: ProfileEntity)
    suspend fun deleteProfileById(item: ProfileEntity)

    suspend fun getFavorite(): List<FavoritesEntity>?
    suspend fun getFavorite(slug: String): Boolean
    suspend fun insertFavorite(entity: FavoritesEntity)
    suspend fun deleteFavorite(slug: String)
    suspend fun clearFavorite()
}