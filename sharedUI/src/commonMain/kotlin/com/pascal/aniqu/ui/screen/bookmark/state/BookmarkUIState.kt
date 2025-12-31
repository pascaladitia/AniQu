package com.pascal.aniqu.ui.screen.bookmark.state

data class BookmarkUIState(
    val isLoading: Boolean = false,
    val error: Pair<Boolean, String> = false to "",
)