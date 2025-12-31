package com.pascal.aniqu.data.local.database

import androidx.room.ConstructedBy
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.RoomDatabaseConstructor
import androidx.room.TypeConverters
import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import com.pascal.aniqu.data.local.dao.FavoritesDao
import com.pascal.aniqu.data.local.dao.ProfileDao
import com.pascal.aniqu.data.local.entity.FavoritesEntity
import com.pascal.aniqu.data.local.entity.ProfileEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO

@TypeConverters(Converters::class)
@ConstructedBy(AppDatabaseConstructor::class)
@Database(
    entities = [
        ProfileEntity::class,
        FavoritesEntity::class
    ], version = 1
)
abstract class AppDatabase : RoomDatabase(), DB {
    abstract fun profileDao(): ProfileDao
    abstract fun favoritesDao(): FavoritesDao
    override fun clearAllTables(): Unit {}
}

@Suppress("KotlinNoActualForExpect")
expect object AppDatabaseConstructor : RoomDatabaseConstructor<AppDatabase> {
    override fun initialize(): AppDatabase
}

interface DB {
    fun clearAllTables(): Unit {}
}

fun getRoomDatabase(
    builder: RoomDatabase.Builder<AppDatabase>
): AppDatabase {
    return builder
        .setDriver(BundledSQLiteDriver())
        .setQueryCoroutineContext(Dispatchers.IO)
        .build()
}