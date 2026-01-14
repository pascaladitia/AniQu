package com.pascal.aniqu.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.pascal.aniqu.data.local.entity.FavoritesEntity

@Dao
interface FavoritesDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavorite(cachedTest: FavoritesEntity) : Long

    @Delete
    suspend fun deleteFavorite(item: FavoritesEntity) : Int

    @Query("SELECT * FROM favorite")
    suspend fun getFavoriteList(): List<FavoritesEntity>?

    @Query("SELECT * FROM favorite WHERE LOWER(title) = LOWER(:title) LIMIT 1")
    suspend fun getFavorite(title: String): FavoritesEntity?

    @Query("DELETE FROM favorite")
    suspend fun clearFavoritesTable()
}