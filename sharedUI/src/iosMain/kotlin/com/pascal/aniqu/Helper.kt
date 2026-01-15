package com.pascal.aniqu

import co.touchlab.kermit.Logger
import co.touchlab.kermit.platformLogWriter
import com.pascal.aniqu.di.start
import org.koin.core.KoinApplication

fun initKoin() {
    KoinApplication.start()

    Logger.setLogWriters(platformLogWriter())
    Logger.i("Kermit iOS initialized")
}