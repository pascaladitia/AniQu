package com.pascal.aniqu.di

import androidx.room.RoomDatabase
import com.pascal.aniqu.data.local.database.AppDatabase
import com.pascal.aniqu.data.local.database.getRoomDatabase
import com.pascal.aniqu.data.local.repository.LocalRepository
import com.pascal.aniqu.data.local.repository.LocalRepositoryImpl
import com.pascal.aniqu.data.remote.api.AnimeClientApi
import com.pascal.aniqu.data.repository.anime.AnimeRepository
import com.pascal.aniqu.data.repository.anime.AnimeRepositoryImpl
import com.pascal.aniqu.domain.usecase.local.LocalUseCase
import com.pascal.aniqu.domain.usecase.local.LocalUseCaseImpl
import com.pascal.aniqu.domain.usecase.anime.AnimeUseCase
import com.pascal.aniqu.domain.usecase.anime.AnimeUseCaseImpl
import com.pascal.aniqu.getDatabaseBuilder
import com.pascal.aniqu.ui.screen.detail.DetailViewModel
import com.pascal.aniqu.ui.screen.favorite.FavoriteViewModel
import com.pascal.aniqu.ui.screen.home.HomeViewModel
import com.pascal.aniqu.ui.screen.profile.ProfileViewModel
import com.pascal.aniqu.ui.screen.manga.MangaViewModel
import com.pascal.aniqu.ui.screen.search.SearchViewModel
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
    singleOf(::AnimeClientApi)

    // Repository
    singleOf(::AnimeRepositoryImpl) { bind<AnimeRepository>() }

    // UseCases
    singleOf(::LocalUseCaseImpl) { bind<LocalUseCase>() }
    singleOf(::AnimeUseCaseImpl) { bind<AnimeUseCase>() }

    // ViewModels
    singleOf(::HomeViewModel)
    singleOf(::MangaViewModel)
    singleOf(::SearchViewModel)
    singleOf(::FavoriteViewModel)
    singleOf(::ProfileViewModel)
    singleOf(::DetailViewModel)
}