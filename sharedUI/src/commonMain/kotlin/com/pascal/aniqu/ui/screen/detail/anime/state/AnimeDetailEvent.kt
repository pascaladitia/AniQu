package com.pascal.aniqu.ui.screen.detail.anime.state

import androidx.compose.runtime.Stable
import androidx.compose.runtime.compositionLocalOf

val LocalAnimeDetailEvent = compositionLocalOf { AnimeDetailEvent() }

@Stable
data class AnimeDetailEvent (
    val onNavBack: () -> Unit = {},
    val onEpisodeSelected: (String) -> Unit = {},
    val onServerSelected: (String) -> Unit = {}
)