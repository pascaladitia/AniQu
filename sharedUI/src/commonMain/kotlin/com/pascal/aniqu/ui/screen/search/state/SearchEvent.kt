package com.pascal.aniqu.ui.screen.search.state

import androidx.compose.runtime.Stable
import androidx.compose.runtime.compositionLocalOf

val LocalSearchEvent = compositionLocalOf { SearchEvent() }

@Stable
data class SearchEvent (
    val onSearch: (String) -> Unit = {},
    val onGenre: (String) -> Unit = {},
    val onDetail: (String) -> Unit = {}
)