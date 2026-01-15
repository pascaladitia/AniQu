package com.pascal.aniqu.di

import com.pascal.aniqu.utils.download.KtorDownloader
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

