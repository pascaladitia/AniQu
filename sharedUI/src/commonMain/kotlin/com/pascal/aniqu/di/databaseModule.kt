package com.pascal.aniqu.di

import androidx.room.RoomDatabase
import com.pascal.aniqu.data.local.database.AppDatabase
import com.pascal.aniqu.data.local.database.getRoomDatabase
import com.pascal.aniqu.getDatabaseBuilder
import org.koin.dsl.module

val databaseModule = module {

    single<RoomDatabase.Builder<AppDatabase>> {
        getDatabaseBuilder()
    }

    single<AppDatabase> {
        getRoomDatabase(get())
    }
}
