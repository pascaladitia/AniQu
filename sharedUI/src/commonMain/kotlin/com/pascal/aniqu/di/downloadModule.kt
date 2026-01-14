package com.pascal.aniqu.di

import com.pascal.aniqu.utils.KtorDownloader
import okio.FileSystem
import okio.SYSTEM
import org.koin.dsl.module

val downloadModule = module {

    single { FileSystem.SYSTEM }

    single {
        KtorDownloader(
            client = get(),
            fileSystem = get()
        )
    }
}

