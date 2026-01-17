package com.pascal.aniqu.ui.screen.manga.state

import com.pascal.aniqu.domain.model.manga.Manga

data class MangaUIState(
    val isLoading: Boolean = false,
    val error: Pair<Boolean, String> = false to "",
    val mangaResponse: Manga? = null
)