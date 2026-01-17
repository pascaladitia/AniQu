package com.pascal.aniqu.di

import org.koin.core.KoinApplication
import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration

fun initKoin(enableNetworkLogs: Boolean = true, appDeclaration: KoinAppDeclaration = {}) =
    startKoin {
        appDeclaration()
        modules(
            databaseModule,
            networkModule,
            repositoryModule,
            useCaseModule,
            downloadModule,
            viewModelModule
        )
    }

fun KoinApplication.Companion.start(): KoinApplication = initKoin { }

