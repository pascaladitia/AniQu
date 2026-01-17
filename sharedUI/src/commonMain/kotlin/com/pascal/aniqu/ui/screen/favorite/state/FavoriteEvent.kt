package com.pascal.aniqu.ui.screen.favorite.state

import androidx.compose.runtime.Stable
import androidx.compose.runtime.compositionLocalOf

val LocalFavoriteEvent = compositionLocalOf { FavoriteEvent() }

@Stable
data class FavoriteEvent(
    val onSearch: (String) -> Unit = {},
    val onDetail: (String) -> Unit = {}
)