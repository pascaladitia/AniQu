package com.pascal.aniqu.ui.screen.home.detail.anime.state

data class AnimeDetailUIState(
    val isLoading: Boolean = false,
    val error: Pair<Boolean, String> = false to ""
)