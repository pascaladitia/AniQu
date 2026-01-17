package com.pascal.aniqu.ui.screen.detail.streaming.state

import androidx.compose.runtime.Stable
import androidx.compose.runtime.compositionLocalOf
import com.pascal.aniqu.domain.model.anime.Download
import com.pascal.aniqu.domain.model.anime.Stream

val LocalAnimeStreamingEvent = compositionLocalOf { AnimeStreamingEvent() }

@Stable
data class AnimeStreamingEvent (
    val onNavBack: () -> Unit = {},
    val onEpisodeSelected: (String) -> Unit = {},
    val onDownloadSelected: (Download) -> Unit = {},
    val onSteamSelected: (Stream) -> Unit = {}
)