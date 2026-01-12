package com.pascal.aniqu.ui.screen.detail.anime.state

import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.SharedTransitionScope
import com.pascal.aniqu.domain.model.anime.AnimeDetail
import com.pascal.aniqu.domain.model.anime.AnimeItem
import com.pascal.aniqu.domain.model.anime.Download
import com.pascal.aniqu.domain.model.anime.Stream
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf

data class AnimeDetailUIState(
    val isLoading: Boolean = false,
    val error: Pair<Boolean, String> = false to "",
    val animeId: String = "",
    val downloadUrl: String = "",
    val streamUrl: String = "",
    val animeDetail: AnimeDetail? = null,
    val downloadList: ImmutableList<Download> = persistentListOf(),
    val streamList: ImmutableList<Stream> = persistentListOf(),
    val recomendList: ImmutableList<AnimeItem> = persistentListOf(),

    val sharedTransitionScope: SharedTransitionScope? = null,
    val animatedVisibilityScope: AnimatedVisibilityScope? = null
)