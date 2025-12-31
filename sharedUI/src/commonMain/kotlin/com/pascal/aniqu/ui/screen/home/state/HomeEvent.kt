package com.pascal.aniqu.ui.screen.home.state

import androidx.compose.runtime.Stable
import androidx.compose.runtime.compositionLocalOf
import com.pascal.aniqu.data.local.entity.FavoritesEntity

val LocalHomeEvent = compositionLocalOf { HomeEvent() }

@Stable
data class HomeEvent(
    val onDetail: (FavoritesEntity?) -> Unit = {}
)