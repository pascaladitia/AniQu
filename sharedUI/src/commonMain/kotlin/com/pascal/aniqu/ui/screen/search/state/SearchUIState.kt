package com.pascal.aniqu.ui.screen.search.state

import com.pascal.aniqu.domain.model.anime.AnimeItem
import com.pascal.aniqu.domain.model.anime.AnimeGenre

data class SearchUIState(
    val isSearch: Boolean = false,
    val isLoading: Boolean = false,
    val error: Pair<Boolean, String> = false to "",
    val selectedGenre: String = "",
    val animeList: MutableList<AnimeItem> = mutableListOf(),
    val animeByGenreList: MutableList<AnimeItem> = mutableListOf(),
    val animeGenreList: MutableList<AnimeGenre> = mutableListOf()
)