package com.pascal.aniqu.ui.screen.search.state

import com.pascal.aniqu.domain.model.item.AnimeItem
import com.pascal.aniqu.domain.model.item.Genre

data class SearchUIState(
    val isSearch: Boolean = false,
    val isLoading: Boolean = false,
    val error: Pair<Boolean, String> = false to "",
    val animeList: MutableList<AnimeItem> = mutableListOf(),
    val animeByGenreList: MutableList<AnimeItem> = mutableListOf(),
    val genreList: MutableList<Genre> = mutableListOf()
)