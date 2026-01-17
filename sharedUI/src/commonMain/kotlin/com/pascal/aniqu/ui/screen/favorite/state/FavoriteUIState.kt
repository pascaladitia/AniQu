package com.pascal.aniqu.ui.screen.favorite.state

import com.pascal.aniqu.domain.model.anime.AnimeItem
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf

data class FavoriteUIState(
    val isLoading: Boolean = false,
    val error: Pair<Boolean, String> = false to "",
    val animeList: ImmutableList<AnimeItem> = persistentListOf()
)