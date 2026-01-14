package com.pascal.aniqu.di

import com.pascal.aniqu.data.local.repository.LocalRepository
import com.pascal.aniqu.data.local.repository.LocalRepositoryImpl
import com.pascal.aniqu.data.repository.anime.AnimeRepository
import com.pascal.aniqu.data.repository.anime.AnimeRepositoryImpl
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val repositoryModule = module {

    singleOf(::LocalRepositoryImpl) {
        bind<LocalRepository>()
    }

    singleOf(::AnimeRepositoryImpl) {
        bind<AnimeRepository>()
    }
}
