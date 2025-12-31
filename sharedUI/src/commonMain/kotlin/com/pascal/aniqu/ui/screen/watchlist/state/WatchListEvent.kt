package com.pascal.aniqu.ui.screen.watchlist.state

import androidx.compose.runtime.Stable
import androidx.compose.runtime.compositionLocalOf

val LocalWatchListEvent = compositionLocalOf { WatchListEvent() }

@Stable
data class WatchListEvent(
    val onDetail: () -> Unit = {}
)