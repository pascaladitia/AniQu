package com.pascal.aniqu.ui.screen.manga.state

data class MangaUIState(
    val isLoading: Boolean = false,
    val error: Pair<Boolean, String> = false to ""
)