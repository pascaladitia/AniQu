package com.pascal.aniqu.ui.screen.bookmark.state

import androidx.compose.runtime.Stable
import androidx.compose.runtime.compositionLocalOf

val LocalBookmarkEvent = compositionLocalOf { BookmarkEvent() }

@Stable
data class BookmarkEvent(
    val onNavBack: () -> Unit = {},
    val onAudio: (String?) -> Unit = {},
    val onShare: (String?) -> Unit = {},
)