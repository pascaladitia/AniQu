package com.pascal.aniqu.ui.screen.detail.manga.state

import com.pascal.aniqu.ui.screen.profile.ThemeMode

data class MangaDetailUIState(
    val isLoading: Boolean = false,
    val error: Pair<Boolean, String> = false to "",
    val themeMode: ThemeMode = ThemeMode.SYSTEM
)