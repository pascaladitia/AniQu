package com.pascal.aniqu.ui.screen.detail.manga.state

import androidx.compose.runtime.Stable
import androidx.compose.runtime.compositionLocalOf

val LocalMangaDetailEvent = compositionLocalOf { MangaDetailEvent() }

@Stable
data class MangaDetailEvent(
    val onBookmark: () -> Unit = {},
    val onTheme: () -> Unit = {}
)