package com.pascal.aniqu.ui.screen.profile.state

import com.pascal.aniqu.ui.screen.profile.ThemeMode

data class ProfileUIState(
    val isLoading: Boolean = false,
    val error: Pair<Boolean, String> = false to "",
    val themeMode: ThemeMode = ThemeMode.SYSTEM
)