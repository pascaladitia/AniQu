package com.pascal.aniqu.ui.screen.detail.anime.state

import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.SharedTransitionScope
import com.pascal.aniqu.domain.model.AnimeDetail
import com.pascal.aniqu.domain.model.AnimeEpisodeDetail

data class AnimeDetailUIState(
    val isLoading: Boolean = false,
    val error: Pair<Boolean, String> = false to "",
    val animeId: String = "",
    val streamingUrl: String = "",
    val animeDetail: AnimeDetail? = null,
    val episodeDetail: AnimeEpisodeDetail? = null,

    val sharedTransitionScope: SharedTransitionScope? = null,
    val animatedVisibilityScope: AnimatedVisibilityScope? = null
)