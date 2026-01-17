package com.pascal.aniqu.di

import com.pascal.aniqu.data.remote.api.AnimeClientApi
import com.pascal.aniqu.data.remote.client
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val networkModule = module {

    single { client }

    singleOf(::AnimeClientApi)
}
