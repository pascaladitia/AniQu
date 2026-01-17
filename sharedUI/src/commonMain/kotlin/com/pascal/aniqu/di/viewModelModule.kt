package com.pascal.aniqu.di

import com.pascal.aniqu.ui.screen.detail.anime.AnimeDetailViewModel
import com.pascal.aniqu.ui.screen.detail.streaming.AnimeStreamingViewModel
import com.pascal.aniqu.ui.screen.favorite.FavoriteViewModel
import com.pascal.aniqu.ui.screen.home.HomeViewModel
import com.pascal.aniqu.ui.screen.manga.MangaViewModel
import com.pascal.aniqu.ui.screen.profile.ProfileViewModel
import com.pascal.aniqu.ui.screen.search.SearchViewModel
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val viewModelModule = module {
    singleOf(::HomeViewModel)
    singleOf(::MangaViewModel)
    singleOf(::SearchViewModel)
    singleOf(::FavoriteViewModel)
    singleOf(::ProfileViewModel)
    singleOf(::AnimeDetailViewModel)
    singleOf(::AnimeStreamingViewModel)
}
