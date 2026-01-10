package com.pascal.aniqu.ui.screen.home.detail.anime.state

import androidx.compose.runtime.Stable
import androidx.compose.runtime.compositionLocalOf

val LocalAnimeDetailEvent = compositionLocalOf { AnimeDetailEvent() }

@Stable
data class AnimeDetailEvent (
    val onNavBack: () -> Unit = {}
)