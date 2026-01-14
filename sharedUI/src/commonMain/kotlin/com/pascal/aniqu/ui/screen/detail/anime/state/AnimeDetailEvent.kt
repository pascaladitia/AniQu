package com.pascal.aniqu.ui.screen.detail.anime.state

import androidx.compose.runtime.Stable
import androidx.compose.runtime.compositionLocalOf
import com.pascal.aniqu.domain.model.anime.AnimeDetail
import com.pascal.aniqu.domain.model.anime.Download
import com.pascal.aniqu.domain.model.anime.Stream

val LocalAnimeDetailEvent = compositionLocalOf { AnimeDetailEvent() }

@Stable
data class AnimeDetailEvent (
    val onFavorite: (AnimeDetail?) -> Unit = {},
    val onEpisodeSelected: (String) -> Unit = {},
    val onDownloadSelected: (Download) -> Unit = {},
    val onSteamSelected: (Stream) -> Unit = {},
    val onNavPlayStream: (Stream) -> Unit = {},
    val onNavBack: () -> Unit = {}
)