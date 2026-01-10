package com.pascal.aniqu.ui.screen.detail.anime.state

import com.pascal.aniqu.domain.model.AnimeDetail

data class AnimeDetailUIState(
    val isLoading: Boolean = false,
    val error: Pair<Boolean, String> = false to "",
    val animeDetail: AnimeDetail? = null
)