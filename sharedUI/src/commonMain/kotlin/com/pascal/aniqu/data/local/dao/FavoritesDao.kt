package com.pascal.aniqu.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.pascal.aniqu.data.local.entity.FavoritesEntity

@Dao
interface FavoritesDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavorite(cachedTest: FavoritesEntity) : Long

    @Query("SELECT * FROM favorite")
    suspend fun getFavoriteList(): List<FavoritesEntity>

    @Query("SELECT * FROM favorite WHERE LOWER(slug) = LOWER(:slug) LIMIT 1")
    suspend fun getFavorite(slug: String): FavoritesEntity?

    @Query("DELETE FROM favorite WHERE LOWER(slug) = LOWER(:slug)")
    suspend fun deleteFavorite(slug: String) : Int

    @Query("DELETE FROM favorite")
    suspend fun clearFavoritesTable()
}