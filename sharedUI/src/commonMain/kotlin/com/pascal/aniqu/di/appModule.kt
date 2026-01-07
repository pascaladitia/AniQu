package com.pascal.aniqu.di

import androidx.room.RoomDatabase
import com.pascal.aniqu.data.local.database.AppDatabase
import com.pascal.aniqu.data.local.database.getRoomDatabase
import com.pascal.aniqu.data.local.repository.LocalRepository
import com.pascal.aniqu.data.local.repository.LocalRepositoryImpl
import com.pascal.aniqu.data.remote.api.KtorClientApi
import com.pascal.aniqu.data.repository.RemoteRepository
import com.pascal.aniqu.data.repository.RemoteRepositoryImpl
import com.pascal.aniqu.domain.usecase.local.LocalUseCase
import com.pascal.aniqu.domain.usecase.local.LocalUseCaseImpl
import com.pascal.aniqu.domain.usecase.remote.RemoteUseCase
import com.pascal.aniqu.domain.usecase.remote.RemoteUseCaseImpl
import com.pascal.aniqu.getDatabaseBuilder
import com.pascal.aniqu.ui.screen.bookmark.BookmarkViewModel
import com.pascal.aniqu.ui.screen.detail.DetailViewModel
import com.pascal.aniqu.ui.screen.favorite.FavoriteViewModel
import com.pascal.aniqu.ui.screen.home.HomeViewModel
import com.pascal.aniqu.ui.screen.profile.ProfileViewModel
import com.pascal.aniqu.ui.screen.manga.WatchListViewModel
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val appModule = module {
    // Database
    single<RoomDatabase.Builder<AppDatabase>> { getDatabaseBuilder() }
    single<AppDatabase> { getRoomDatabase(get()) }

    // Data source
    singleOf(::LocalRepositoryImpl) { bind<LocalRepository>() }

    // API client
    singleOf(::KtorClientApi)

    // Repository
    singleOf(::RemoteRepositoryImpl) { bind<RemoteRepository>() }

    // UseCases
    singleOf(::LocalUseCaseImpl) { bind<LocalUseCase>() }
    singleOf(::RemoteUseCaseImpl) { bind<RemoteUseCase>() }

    // ViewModels
    singleOf(::HomeViewModel)
    singleOf(::FavoriteViewModel)
    singleOf(::ProfileViewModel)
    singleOf(::DetailViewModel)
    singleOf(::BookmarkViewModel)
    singleOf(::WatchListViewModel)
}