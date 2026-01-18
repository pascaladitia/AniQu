package com.pascal.aniqu.di

import com.pascal.aniqu.domain.usecase.anime.AnimeUseCase
import com.pascal.aniqu.domain.usecase.anime.AnimeUseCaseImpl
import com.pascal.aniqu.domain.usecase.local.LocalUseCase
import com.pascal.aniqu.domain.usecase.local.LocalUseCaseImpl
import com.pascal.aniqu.domain.usecase.manga.MangaUseCase
import com.pascal.aniqu.domain.usecase.manga.MangaUseCaseImpl
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val useCaseModule = module {

    singleOf(::LocalUseCaseImpl) {
        bind<LocalUseCase>()
    }

    singleOf(::AnimeUseCaseImpl) {
        bind<AnimeUseCase>()
    }

    singleOf(::MangaUseCaseImpl) {
        bind<MangaUseCase>()
    }
}
