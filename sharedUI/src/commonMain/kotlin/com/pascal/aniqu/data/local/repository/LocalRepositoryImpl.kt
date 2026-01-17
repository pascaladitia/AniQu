package com.pascal.aniqu.data.local.repository

import com.pascal.aniqu.data.local.database.AppDatabase
import com.pascal.aniqu.data.local.entity.FavoritesEntity
import com.pascal.aniqu.data.local.entity.ProfileEntity
import org.koin.core.annotation.Single

@Single
class LocalRepositoryImpl(
    private val database: AppDatabase,
) : LocalRepository {

    // Profile
    override suspend fun getProfileById(id: Long): ProfileEntity? {
        return database.profileDao().getProfileById(id)
    }

    override suspend fun getAllProfiles(): List<ProfileEntity> {
        return database.profileDao().getAllProfiles()
    }

    override suspend fun deleteProfileById(item: ProfileEntity) {
        return database.profileDao().deleteProfile(item)
    }

    override suspend fun insertProfile(item: ProfileEntity) {
        return database.profileDao().insertProfile(item)
    }

    // Favorites
    override suspend fun getFavorite(): List<FavoritesEntity> {
        return database.favoritesDao().getFavoriteList()
    }

    override suspend fun getFavorite(slug: String): Boolean {
        return database.favoritesDao().getFavorite(slug) != null
    }

    override suspend fun insertFavorite(entity: FavoritesEntity) {
        database.favoritesDao().insertFavorite(entity)
    }

    override suspend fun deleteFavorite(slug: String) {
        database.favoritesDao().deleteFavorite(slug)
    }

    override suspend fun clearFavorite() {
        return database.favoritesDao().clearFavoritesTable()
    }
}