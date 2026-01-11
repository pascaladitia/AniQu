package com.pascal.aniqu.ui.screen.detail.anime.state

import androidx.compose.runtime.Stable
import androidx.compose.runtime.compositionLocalOf
import com.pascal.aniqu.domain.model.anime.Download

val LocalAnimeDetailEvent = compositionLocalOf { AnimeDetailEvent() }

@Stable
data class AnimeDetailEvent (
    val onNavBack: () -> Unit = {},
    val onEpisodeSelected: (String) -> Unit = {},
    val onServerSelected: (Download) -> Unit = {}
)