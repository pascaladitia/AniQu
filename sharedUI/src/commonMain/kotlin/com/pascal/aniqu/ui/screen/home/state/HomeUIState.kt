package com.pascal.aniqu.ui.screen.home.state

import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import com.pascal.aniqu.domain.model.anime.AnimeItem
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf

@ExperimentalSharedTransitionApi
data class HomeUIState(
    val isLoading: Boolean = false,
    val error: Pair<Boolean, String> = false to "",
    val animeList: ImmutableList<AnimeItem> = persistentListOf(),
    val animeGenreList: ImmutableList<AnimeItem> = persistentListOf(),
    val sharedTransitionScope: SharedTransitionScope? = null,
    val animatedVisibilityScope: AnimatedVisibilityScope? = null
)